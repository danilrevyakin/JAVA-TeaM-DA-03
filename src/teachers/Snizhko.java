package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.SnizhkoSkill;

import java.io.Serializable;
import java.util.ArrayList;

public class Snizhko extends Teacher implements Serializable {
    Skill skill = new SnizhkoSkill();

    public Snizhko(String name, boolean sex, ArrayList<Question> questions) {
        super(name, sex, questions);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void correctStudentAnswerSkill() {

        if(Probability.eventProbability(60))
            skill.studentAnswerCorrect(super.student, super.lastQuestion);
    }

    @Override
    protected void wrongStudentAnswerSkill() {
        if(Probability.eventProbability(45))
            skill.studentAnswerFalse(student, lastQuestion);
    }
}
