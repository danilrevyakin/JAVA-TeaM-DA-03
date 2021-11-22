package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.SnizhkoSkill;

import java.io.Serializable;
import java.util.ArrayList;

public class Snizhko extends Teacher implements Serializable {
    private final int correctSkillProbability = 60;
    private final int wrongSkillProbability = 45;
    Skill skill = new SnizhkoSkill();
    public Snizhko(String name, boolean sex, ArrayList<Question> questions) {
        super(name, sex, questions);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void correctStudentAnswerSkill() {

        if(Probability.eventProbability(correctSkillProbability))
            skill.studentAnswerCorrect(super.student, super.lastQuestion);
    }

    @Override
    protected void wrongStudentAnswerSkill() {
        if(Probability.eventProbability(wrongSkillProbability))
            skill.studentAnswerFalse(student, lastQuestion);
    }
}
