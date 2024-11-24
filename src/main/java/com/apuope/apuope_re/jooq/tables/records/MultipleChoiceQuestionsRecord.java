/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables.records;


import com.apuope.apuope_re.jooq.tables.MultipleChoiceQuestions;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class MultipleChoiceQuestionsRecord extends UpdatableRecordImpl<MultipleChoiceQuestionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>apuope.multiple_choice_questions.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.quiz_id</code>.
     */
    public void setQuizId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.quiz_id</code>.
     */
    public Integer getQuizId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.question_id</code>.
     */
    public void setQuestionId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.question_id</code>.
     */
    public Integer getQuestionId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.question</code>.
     */
    public void setQuestion(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.question</code>.
     */
    public String getQuestion() {
        return (String) get(3);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.option_a</code>.
     */
    public void setOptionA(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.option_a</code>.
     */
    public String getOptionA() {
        return (String) get(4);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.option_b</code>.
     */
    public void setOptionB(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.option_b</code>.
     */
    public String getOptionB() {
        return (String) get(5);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.option_c</code>.
     */
    public void setOptionC(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.option_c</code>.
     */
    public String getOptionC() {
        return (String) get(6);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.correct_option</code>.
     */
    public void setCorrectOption(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.correct_option</code>.
     */
    public String getCorrectOption() {
        return (String) get(7);
    }

    /**
     * Setter for <code>apuope.multiple_choice_questions.points</code>.
     */
    public void setPoints(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>apuope.multiple_choice_questions.points</code>.
     */
    public Integer getPoints() {
        return (Integer) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MultipleChoiceQuestionsRecord
     */
    public MultipleChoiceQuestionsRecord() {
        super(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS);
    }

    /**
     * Create a detached, initialised MultipleChoiceQuestionsRecord
     */
    public MultipleChoiceQuestionsRecord(Integer id, Integer quizId, Integer questionId, String question, String optionA, String optionB, String optionC, String correctOption, Integer points) {
        super(MultipleChoiceQuestions.MULTIPLE_CHOICE_QUESTIONS);

        setId(id);
        setQuizId(quizId);
        setQuestionId(questionId);
        setQuestion(question);
        setOptionA(optionA);
        setOptionB(optionB);
        setOptionC(optionC);
        setCorrectOption(correctOption);
        setPoints(points);
        resetChangedOnNotNull();
    }
}
