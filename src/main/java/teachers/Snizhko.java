package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;

import java.io.Serializable;

import java.util.List;

public class Snizhko extends Teacher implements Serializable {
    private final int correctSkillProbability = 60;
    private final int wrongSkillProbability = 45;
    public Snizhko(String name, String sex, List<Question> questions, int id) {
        super(name, sex, questions, id);
    }

    @Override
    protected void correctStudentAnswerSkill() {

        if(Probability.eventProbability(correctSkillProbability))
            mode.studentAnswerCorrect(super.getStudent());
    }

    @Override
    protected void wrongStudentAnswerSkill() {
        if(Probability.eventProbability(wrongSkillProbability))
            mode.studentAnswerFalse(super.getStudent());
    }
}
