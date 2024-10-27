CREATE TABLE apuope.session (
                                id SERIAL PRIMARY KEY,
                                account_id INT NOT NULL,
                                log_in TIMESTAMP NOT NULL,
                                log_out TIMESTAMP NULL,
                                CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES apuope.users(id)
);