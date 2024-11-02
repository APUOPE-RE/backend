CREATE TABLE apuope.message (
    id SERIAL PRIMARY KEY,
    conversation_id INT NOT NULL,
    text VARCHAR(1000) NOT NULL,
    datetime TIMESTAMP NULL,
    CONSTRAINT fk_account FOREIGN KEY (conversation_id) REFERENCES apuope.conversation(id)
);