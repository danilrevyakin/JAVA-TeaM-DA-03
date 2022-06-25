package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;

import java.io.Serializable;

import java.util.List;

public class Snizhko extends Teacher implements Serializable {
    public Snizhko(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
        super(name, sex, questions, id, 60, 45);
    }

    public String SnizhkoSkillCorrectMessage(int k){
        return ("I like you, add " + k + " points to your score!");
    }
    public String SnizhkoSkillWrongMessage(){
        return ("You should study more");
    }

    @Override
    protected String correctStudentReaction() {
        int k = rand.nextInt(10);
        getStudent().increaseScoreOn(k);
        return super.correctStudentReaction() + SnizhkoSkillCorrectMessage(k);
    }

    @Override
    protected String wrongStudentReaction() {
        return super.wrongStudentReaction() + SnizhkoSkillWrongMessage();
    }
}
