from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import subprocess
import json
import os
import logging

# -----------------------------
# Logging
# -----------------------------
logging.basicConfig(level=logging.INFO, format="%(asctime)s - %(levelname)s - %(message)s")
logger = logging.getLogger("ClinicalSummaryService")

# -----------------------------
# FastAPI app
# -----------------------------
app = FastAPI(title="Clinical Summary API", version="1.2.0")

# Enable CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# -----------------------------
# Ollama config
# -----------------------------
OLLAMA_PATH = os.getenv(
    "OLLAMA_PATH",
    r"C:\Users\user\AppData\Local\Programs\Ollama\ollama.exe"
)

MODEL_NAME = "llama2:13b"


# -----------------------------
# Input schema
# -----------------------------
class PatientData(BaseModel):
    # Age is now a single string field (e.g. "5 years", "3 months", "2 days", "35")
    Age: str
    Gender: str
    Diagnosis: str
    Symptoms: list[str] = []
    Medications: list[str] = []
    Surgeries: list[str] = []
    Allergies: list[str] = []
    Medical_Warnings: list[str] = []
    Problems: list[str] = []
    Vitals: dict = {}


def generate_summary(patient_data: dict) -> str:
    # --- Use age exactly as provided (no calculation / conversion) ---
    raw_age = patient_data.get("Age", "N/A")

    # Turn into a string but don't change its content
    age_text = str(raw_age) if raw_age is not None else "N/A"

    gender = patient_data.get("Gender", "N/A")
    diagnosis = patient_data.get("Diagnosis", "N/A")

    # First sentence uses the raw strings
    parts = [f"A {age_text} {gender} with {diagnosis}"]

    if patient_data.get("Symptoms"):
        parts.append("Symptoms: " + ", ".join(patient_data["Symptoms"]))
    if patient_data.get("Medications"):
        parts.append("Medications: " + ", ".join(patient_data["Medications"]))
    if patient_data.get("Surgeries"):
        parts.append("Past surgeries: " + ", ".join(patient_data["Surgeries"]))
    if patient_data.get("Allergies"):
        parts.append("Allergies: " + ", ".join(patient_data["Allergies"]))
    if patient_data.get("Medical_Warnings"):
        parts.append("Medical warnings: " + ", ".join(patient_data["Medical_Warnings"]))
    if patient_data.get("Problems"):
        parts.append("Comorbidities: " + ", ".join(patient_data["Problems"]))
    if patient_data.get("Vitals"):
        vitals = patient_data["Vitals"]
        vitals_text = ", ".join([f"{k} {v}" for k, v in vitals.items()])
        parts.append("Vital signs: " + vitals_text)

    patient_text = ". ".join(parts)

    prompt = f"""
Rephrase the following patient data into ONE concise, coherent clinical summary paragraph.
ONLY include the information provided below.
DO NOT add any extra details, hallucinations, or assumptions.
KEEP all abbreviations and text exactly as they appear. Do NOT change numbers, units, or wording.

Patient data:
{patient_text}

Clinical Summary:
"""

    # Log full input data
    logger.info(f"Full PatientData input: {json.dumps(patient_data, ensure_ascii=False)}")

    logger.info("Running Ollama model...")

    try:
        result = subprocess.run(
            [OLLAMA_PATH, "run", MODEL_NAME],
            input=prompt,
            capture_output=True,
            text=True,
            encoding="utf-8",
            errors="ignore"
        )
    except FileNotFoundError:
        raise RuntimeError(f"Ollama executable not found at {OLLAMA_PATH}")

    if result.returncode != 0:
        raise RuntimeError(result.stderr.strip())

    raw_output = result.stdout.strip()
    logger.info(f"Ollama output:\n{raw_output}")

    lines = raw_output.splitlines()
    cleaned = []
    skip_words = ["clinical summary", "summary:", "here is", "okay"]

    for line in lines:
        low = line.lower().strip()
        if line.strip() and not any(w in low for w in skip_words):
            cleaned.append(line.strip())

    return " ".join(cleaned).strip()


@app.post("/summarize")
def summarize(patient: PatientData):
    try:
        summary = generate_summary(patient.dict())
        return {"ClinicalSummary": summary}
    except Exception as e:
        logger.exception("Summary error")
        raise HTTPException(status_code=500, detail=str(e))


@app.get("/")
def home():
    return {"message": "Clinical Summary Service is running!"}


# -----------------------------
# FIXED ENTRY POINT
# -----------------------------
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(
        "summarization_service:app",
        host="0.0.0.0",
        port=8003,
        reload=True
    )
