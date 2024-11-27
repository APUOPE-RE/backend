package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.*;
import com.apuope.apuope_re.jooq.tables.*;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Repository
public class QuizRepository {
    private static final Integer INITIAL_SCORE = 0;
    private static final Integer ONE_POINT = 1;
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");

    @Autowired
    public QuizRepository(){};

    public QuizData fetchQuizByQuizId(Integer quizId, DSLContext context){
        return context.select()
                .from(Quiz.QUIZ)
                .where(Quiz.QUIZ.ID.eq(quizId))
                .fetchOneInto(QuizData.class);
    }

    public QuizData fetchQuizByAccountId(Integer accountId, DSLContext context){
        return context.select()
                .from(Quiz.QUIZ)
                .where(Quiz.QUIZ.ACCOUNT_ID.eq(accountId))
                .orderBy(Quiz.QUIZ.ID.desc())
                .limit(1)
                .fetchOneInto(QuizData.class);
    }

    public QuestionData fetchQuestionByQuestionId(Integer quizId, Integer questionNumber, DSLContext context){
        return context.select()
                .from(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS)
                .where(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUIZ_ID.eq(quizId))
                .and(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUESTION_NUMBER.eq(questionNumber))
                .fetchOneInto(QuestionData.class);
    }

    public List<QuestionData> fetchQuestionsByQuizId(Integer quizId, DSLContext context){
        return context.select()
                .from(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS)
                .where(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUIZ_ID.eq(quizId))
                .fetchInto(QuestionData.class);
    }

    public List<QuizAnswerData> fetchQuizAnswersByQuizResultId(Integer quizResultId, DSLContext context){
        return context.select()
                .from(QuizAnswers.QUIZ_ANSWERS)
                .where(QuizAnswers.QUIZ_ANSWERS.QUIZ_RESULT_ID.eq(quizResultId))
                .fetchInto(QuizAnswerData.class);
    }

    public QuizResultData fetchQuizResultByQuizId(Integer quizId, DSLContext context){
        return context.select()
                .from(QuizResult.QUIZ_RESULT)
                .where(QuizResult.QUIZ_RESULT.QUIZ_ID.eq(quizId))
                .fetchOneInto(QuizResultData.class);
    }

    public QuizResultData fetchQuizResultByAccountId(Integer accountId, Integer quizId, DSLContext context){
        return context.select()
                .from(QuizResult.QUIZ_RESULT)
                .where(QuizResult.QUIZ_RESULT.ACCOUNT_ID.eq(accountId)).and(QuizResult.QUIZ_RESULT.QUIZ_ID.eq(quizId))
                .orderBy(QuizResult.QUIZ_RESULT.ID.desc())
                .fetchOneInto(QuizResultData.class);
    }

    public void saveQuizAnswer(Integer quizResultId, Integer questionId, Integer questionNumber, String answer, boolean correct, Integer points,
            DSLContext context){
        context.insertInto(QuizAnswers.QUIZ_ANSWERS)
                .set(QuizAnswers.QUIZ_ANSWERS.QUIZ_RESULT_ID, quizResultId)
                .set(QuizAnswers.QUIZ_ANSWERS.QUESTION_ID, questionId)
                .set(QuizAnswers.QUIZ_ANSWERS.QUESTION_NUMBER, questionNumber)
                .set(QuizAnswers.QUIZ_ANSWERS.ANSWER, answer)
                .set(QuizAnswers.QUIZ_ANSWERS.CORRECT, correct)
                .set(QuizAnswers.QUIZ_ANSWERS.POINTS, points)
                .execute();
    }

    public QuizResultData saveQuizResult(Integer accountId, Integer quizId, Integer maxScore, DSLContext context){
        context.insertInto(QuizResult.QUIZ_RESULT)
                .set(QuizResult.QUIZ_RESULT.ACCOUNT_ID, accountId)
                .set(QuizResult.QUIZ_RESULT.QUIZ_ID, quizId)
                .set(QuizResult.QUIZ_RESULT.SCORE, INITIAL_SCORE)
                .set(QuizResult.QUIZ_RESULT.MAX_SCORE, maxScore)
                .set(QuizResult.QUIZ_RESULT.DATETIME, LocalDateTime.now(TIMEZONE))
                .execute();

        return fetchQuizResultByAccountId(accountId, quizId, context);
    }

    public QuizData saveQuiz(List<QuestionData> inputData, Integer accountId, Integer lectureId, DSLContext context) {
        Integer maxPoints = inputData.size();

        context.insertInto(Quiz.QUIZ)
                .set(Quiz.QUIZ.ACCOUNT_ID, accountId)
                .set(Quiz.QUIZ.LECTURE_ID, lectureId)
                .set(Quiz.QUIZ.MAX_POINTS, maxPoints)
                .execute();

        QuizData quizData = fetchQuizByAccountId(accountId, context);

        context.batch(inputData.stream().map(q ->
                context.insertInto(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS)
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUIZ_ID, quizData.getId())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUESTION_NUMBER, q.getQuestionNumber())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.QUESTION, q.getQuestion())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.OPTION_A, q.getOptionA())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.OPTION_B, q.getOptionB())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.OPTION_C, q.getOptionC())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.CORRECT_OPTION, q.getCorrectOption())
                        .set(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS.POINTS, ONE_POINT))
                .toArray(Query[]::new))
                .execute();

        List<QuestionData> questionData = fetchQuestionsByQuizId(quizData.getId(), context);
        quizData.setQuestionDataList(questionData);

        return quizData;
    }
}
