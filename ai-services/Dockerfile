FROM python:3.11-slim

# Optional: install system deps for Pillow/easyocr if needed
RUN apt-get update && apt-get install -y \
    libgl1 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Install Python deps
COPY ai-services/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy all ai-services code
COPY ai-services/ .

# Default command (overridden per-service in docker-compose)
CMD ["uvicorn", "ocr_service:app", "--host", "0.0.0.0", "--port", "8001"]
