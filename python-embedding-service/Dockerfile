# Use an official Python runtime as a parent image
FROM python:3.8-slim

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY embedding_service.py .

EXPOSE 5000

CMD ["python", "embedding_service.py"]
