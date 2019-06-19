package com.londonappbrewery.quizzler;


public class TrueFalse {

  private int questionId;
  private Boolean answer;

  public TrueFalse(int questionId, Boolean answer) {
    this.questionId = questionId;
    this.answer = answer;
  }

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public Boolean getAnswer() {
    return answer;
  }

  public void setAnswer(Boolean answer) {
    this.answer = answer;
  }
}
