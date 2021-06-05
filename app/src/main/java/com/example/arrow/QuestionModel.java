package com.example.arrow;

public class QuestionModel {
    private String Question;
    private String Answer;
    private String Explanation;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;

    public QuestionModel(){}

    public QuestionModel(String question, String answer, String explanation, String optionA, String optionB, String optionC, String optionD) {
        Question = question;
        Answer = answer;
        Explanation = explanation;
        OptionA = optionA;
        OptionB = optionB;
        OptionC = optionC;
        OptionD = optionD;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String optionA) {
        OptionA = optionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String optionB) {
        OptionB = optionB;
    }

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String optionC) {
        OptionC = optionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String optionD) {
        OptionD = optionD;
    }
}
