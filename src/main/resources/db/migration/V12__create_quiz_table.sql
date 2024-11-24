CREATE TABLE apuope.quiz (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    lecture_id INT NOT NULL,
    max_points INT NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES apuope.users(id)
);