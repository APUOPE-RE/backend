CREATE TABLE apuope.quiz_answers (
    id SERIAL PRIMARY KEY,
    quiz_result_id INT NOT NULL,
    question_id INT NOT NULL,
    question_number INT NOT NULL,
    answer VARCHAR(20) NOT NULL,
    correct BOOLEAN NOT NULL,
    points INT NULL,
    CONSTRAINT fk_quiz_result FOREIGN KEY (quiz_result_id) REFERENCES apuope.quiz_result(id),
    CONSTRAINT fk_multiple_choice_questions FOREIGN KEY (question_id) REFERENCES apuope.multiple_choice_questions(id)
);