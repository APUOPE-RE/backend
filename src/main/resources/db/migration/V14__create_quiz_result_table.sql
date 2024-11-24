CREATE TABLE apuope.quiz_result (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT NOT NULL,
    max_score INT NOT NULL,
    datetime TIMESTAMP NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES apuope.users(id),
    CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES apuope.quiz(id)
);