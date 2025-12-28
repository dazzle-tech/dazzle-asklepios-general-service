FROM python:3.11-slim AS builder

WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir --prefix=/install -r requirements.txt

FROM python:3.11-slim

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1

RUN useradd -m appuser
WORKDIR /app

COPY --from=builder /install /usr/local
COPY . .

USER appuser

#CMD ["uvicorn", "ocr_service:app", "--host", "0.0.0.0", "--port", "8001"]
#CMD ["uvicorn", "parsing_service:app", "--host", "0.0.0.0", "--port", "8002"]
CMD ["uvicorn", "summarization_service:app", "--host", "0.0.0.0", "--port", "8003"]
