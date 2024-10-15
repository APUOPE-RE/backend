CREATE TABLE apuope.token (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    uuid UUID UNIQUE DEFAULT gen_random_uuid(),
    expiration_time TIMESTAMP NULL,
    valid BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES apuope.users(id)
);