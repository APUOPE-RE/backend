CREATE TABLE apuope.conversation (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    chapter_id INT,
    datetime TIMESTAMP NULL,
    subject VARCHAR(50) NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES apuope.users(id)
);