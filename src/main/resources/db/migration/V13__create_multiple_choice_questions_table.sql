CREATE TABLE apuope.multiple_choice_questions (
    id SERIAL PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_id INT NOT NULL,
    question VARCHAR(500) NOT NULL,
    option_a VARCHAR(500) NOT NULL,
    option_b VARCHAR(500) NOT NULL,
    option_c VARCHAR(500) NOT NULL,
    correct_option VARCHAR(20),
    points INT NOT NULL,
    CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES apuope.quiz(id)
);