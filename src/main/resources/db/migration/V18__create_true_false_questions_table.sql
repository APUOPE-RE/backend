CREATE TABLE apuope.true_false_questions (
id SERIAL PRIMARY KEY,
quiz_id INT NOT NULL,
question_number INT NOT NULL,
question VARCHAR(500) NOT NULL,
option_a VARCHAR(10) NOT NULL CHECK (option_a IN ('true', 'false')),
option_b VARCHAR(10) NOT NULL CHECK (option_b IN ('true', 'false')),
correct_option VARCHAR(10) NOT NULL CHECK (correct_option IN ('true', 'false')),
points INT NOT NULL,
CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES apuope.quiz(id) ON DELETE CASCADE
);