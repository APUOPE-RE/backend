CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE apuope.textbook_embeddings (
    id SERIAL PRIMARY KEY,
    chunk TEXT,
    embedding vector(384));