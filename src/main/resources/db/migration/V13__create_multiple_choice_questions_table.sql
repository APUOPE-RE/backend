CREATE TABLE apuope.multiple_choice_questions (
    id SERIAL PRIMARY KEY,
    quiz_id INT NOT NULL,
    question VARCHAR(100) NOT NULL,
    choice_a VARCHAR(50) NOT NULL,
    choice_b VARCHAR(50) NOT NULL,
    choice_c VARCHAR(50) NOT NULL,
    correct_answer VARCHAR(10),
    points INT NOT NULL,
    CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES apuope.quiz(id)
);