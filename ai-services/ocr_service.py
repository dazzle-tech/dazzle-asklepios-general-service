from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
import easyocr
from io import BytesIO
from PIL import Image
import numpy as np
import logging
from pathlib import Path
from functools import lru_cache

# ============================================================
# Logging
# ============================================================
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
)
logger = logging.getLogger("OCRService")

# ============================================================
# Paths
# ============================================================
BASE_DIR = Path(__file__).resolve().parent
MODEL_DIR = BASE_DIR.parent / "ai-models" / "easyocr"
MODEL_DIR.mkdir(parents=True, exist_ok=True)

OCR_LANGS = ["en"]


# ============================================================
# Lazy Singleton EasyOCR Reader
# ============================================================
@lru_cache(maxsize=1)
def get_reader():
    logger.info("Initializing EasyOCR reader (one-time load)...")
    reader = easyocr.Reader(
        OCR_LANGS,
        gpu=False,
        model_storage_directory=str(MODEL_DIR),
        download_enabled=True,
    )
    logger.info("EasyOCR reader ready.")
    return reader


# ============================================================
# FastAPI
# ============================================================
app = FastAPI(title="OCR Service")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# ============================================================
# Routes
# ============================================================
@app.get("/")
def home():
    return {"message": "OCR Service is running!"}


@app.post("/extract-text/")
async def extract_text(file: UploadFile = File(...)):
    logger.info(f"Received file: {file.filename}")

    try:
        # Read file bytes
        image_bytes = await file.read()
        if not image_bytes:
            return JSONResponse(
                content={"error": "Empty file received"},
                status_code=400
            )

        # Load into PIL
        image = Image.open(BytesIO(image_bytes)).convert("RGB")
        image_np = np.array(image)

        # OCR
        reader = get_reader()
        results = reader.readtext(image_np, detail=0)

        lines = [line.strip() for line in results if line.strip()]
        logger.info(f"OCR extracted {len(lines)} text lines.")

        return JSONResponse(content={"text_lines": lines})

    except Exception as e:
        logger.error(f"OCR Error: {str(e)}")
        return JSONResponse(content={"error": str(e)}, status_code=500)


# ============================================================
# App Runner (Windows-friendly)
# ============================================================
if __name__ == "__main__":
    import uvicorn

    uvicorn.run(
        "ocr_service:app",
        host="127.0.0.1",      # Best for Windows + reload
        port=8001,
        reload=True,           # Safe now with cached OCR loader
        log_level="info"
    )
