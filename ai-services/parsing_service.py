import subprocess
import json
import re
import os
import logging
from pathlib import Path
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware

# -----------------------------
# Logging configuration
# -----------------------------
logging.basicConfig(level=logging.INFO, format="%(asctime)s - %(levelname)s - %(message)s")
logger = logging.getLogger("ParsingService")

# -----------------------------
# Paths & Ollama model directory
# -----------------------------
BASE_DIR = Path(__file__).resolve().parent
OLLAMA_MODELS_DIR = BASE_DIR.parent / "ai-models" / "ollama"
OLLAMA_MODELS_DIR.mkdir(parents=True, exist_ok=True)

logger.info(f"Ollama model directory: {OLLAMA_MODELS_DIR}")

# -----------------------------
# FastAPI App
# -----------------------------
app = FastAPI(title="Passport/ID Parsing Service")
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # change in production
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# -----------------------------
# Ollama executable
# -----------------------------
OLLAMA_PATH = os.getenv("OLLAMA_PATH", "ollama")
MODEL_NAME = "gemma3:4b"

# -----------------------------
# Run Ollama
# -----------------------------
def run_ollama(extracted_text: str):

    cleaned_text = "\n".join(
        [line.strip() for line in extracted_text.splitlines() if line.strip()]
    )

    prompt = f"""
You are a strict JSON parser for passport/ID OCR text.

Your task:
- Read ONLY the OCR text provided.
- Return ONLY a single valid JSON object with EXACTLY these keys:
  - "Type"
  - "Document Number"
  - "Surname / Family Name"
  - "Given Names"
  - "Nationality"
  - "Date of Birth"
  - "Sex"
  - "Place of Birth"

Gender Field Rules ("Sex"):
1. Use the document text if it explicitly contains:
   - SEX M, SEX F
   - MALE, FEMALE
   - M, F used as sex markers
2. If no explicit marker:
   Infer sex ONLY from culturally obvious first names.
3. If unclear:
   "Sex": ""

General:
- Never add keys.
- Never modify spelling.
- Never hallucinate.
- If field unknown → "".

OCR Text:
{cleaned_text}

JSON Output:
"""

    logger.info("Running Ollama model for parsing...")

    try:
        env = os.environ.copy()
        env["OLLAMA_MODELS"] = str(OLLAMA_MODELS_DIR)

        # Use binary mode to avoid Windows UnicodeDecodeError
        process = subprocess.Popen(
            [OLLAMA_PATH, "run", MODEL_NAME],
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            env=env,
        )

        # Send UTF-8 prompt and receive raw bytes
        output_bytes, error_bytes = process.communicate(prompt.encode("utf-8"))

        output = output_bytes.decode("utf-8", errors="replace") if output_bytes else ""
        error = error_bytes.decode("utf-8", errors="replace") if error_bytes else ""

        logger.info(f"Ollama stdout length: {len(output)}")
        if error.strip():
            logger.info(f"Ollama stderr: {error.strip()}")

        # --------------------------------------------------------
        # Improved JSON extraction logic
        # --------------------------------------------------------

        # 1. Extract JSON inside ```json fenced block
        fenced_match = re.search(
            r"```(?:json)?\s*(\{.*?\})\s*```",
            output,
            flags=re.DOTALL | re.IGNORECASE
        )

        if fenced_match:
            json_str = fenced_match.group(1).strip()
        else:
            # 2. Fallback: extract first {...} block
            json_match = re.search(r"\{.*\}", output, re.DOTALL)
            if not json_match:
                logger.error("No JSON object found in model output.")
                return {"raw_output": output, "error": "Model did not return valid JSON"}
            json_str = json_match.group(0).strip()

        # --------------------------------------------------------
        # Parse the extracted JSON
        # --------------------------------------------------------
        try:
            data = json.loads(json_str)
            logger.info("JSON parsing successful.")
        except Exception as e:
            logger.error(f"JSON decode failed: {e}")
            return {"raw_output": output, "error": "Invalid JSON returned by model"}

        # NOTE: No normalization of Sex here, it is returned exactly as produced by the model
        return data

    except FileNotFoundError:
        msg = (
            f"Ollama executable '{OLLAMA_PATH}' not found. "
            f"Install Ollama or set OLLAMA_PATH."
        )
        logger.error(msg)
        return {"error": msg}

    except Exception as e:
        logger.error(f"Unexpected error while running Ollama: {str(e)}")
        return {"error": str(e)}

# -----------------------------
# API Endpoints
# -----------------------------
@app.post("/parse-json/")
async def parse_document_json(request: Request):
    try:
        body = await request.json()
        if isinstance(body, dict) and "text_lines" in body:
            extracted_text = "\n".join(body["text_lines"])
        else:
            extracted_text = json.dumps(body, ensure_ascii=False, indent=2)

        logger.info(f"Received JSON for parsing. Lines: {len(extracted_text.splitlines())}")

        return JSONResponse(content=run_ollama(extracted_text))

    except Exception as e:
        logger.error(f"Error parsing JSON document: {str(e)}")
        return JSONResponse(content={"error": str(e)}, status_code=500)


@app.get("/")
def home():
    logger.info("Home route accessed.")
    return {"message": "Passport/ID Parsing Service is running!"}


if __name__ == "__main__":
    import uvicorn
    uvicorn.run("parsing_service:app", host="0.0.0.0", port=8002, reload=True)
