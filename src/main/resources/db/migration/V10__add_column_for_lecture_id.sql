ALTER TABLE apuope.textbook_embeddings
    ADD COLUMN chapterId INTEGER;

CREATE INDEX idx_textbook_embeddings_chapterId
    ON apuope.textbook_embeddings (chapterId);