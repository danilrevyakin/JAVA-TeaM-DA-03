package model.teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Teacher;
import model.modes.Medium;

public class Bokhonov extends Teacher {
    String superQuestion = "Ok, I have additional question for you: What year was Richter born?";
    final int correctAnsw = 1915;
    private boolean superQuestionIsUsed = false;

    String badNews = "\nAbsolutely wrong, I want to see you on the commission faster," +
            " so I'll take off half of your health";

    public Bokhonov(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
        super(name, sex, questions, id, 40, 40);
        mode = new Medium();
        manaPrice = 40;
        manaChangePrice = 10;
    }

    @Override
    protected String correctStudentReaction() {
        if(!superQuestionIsUsed){
            superQuestionIsUsed = true;
            return super.correctStudentReaction() + BokhonovReactionSmartStudent();
        }
        return super.correctStudentReaction();
    }

    private String BokhonovReactionSmartStudent() {
        Question hardQuestion = new Question();
        hardQuestion.setQuestion(superQuestion);
        String answer = String.valueOf(correctAnsw);
        hardQuestion.setChoices(new ArrayList<>(List.of(getWrongChoice(), getWrongChoice(), getWrongChoice(), answer)));
        hardQuestion.setAnswer(answer);
        addNextQuestion(hardQuestion);
        return message;
    }

    private String getWrongChoice() {
        return String.valueOf(correctAnsw + rand.nextInt() % 5);
    }

    @Override
    protected String wrongStudentReaction() {
        return super.wrongStudentReaction() + BokhonovReactionOnWrongAnswer();
    }

    private String BokhonovReactionOnWrongAnswer() {
        getStudent().increaseHealthOn(-getStudent().getHealth() / 2);
        return badNews;
    }

}
