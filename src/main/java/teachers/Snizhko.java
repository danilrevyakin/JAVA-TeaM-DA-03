package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.SnizhkoSkill;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Snizhko extends Teacher implements Serializable {
    private final int correctSkillProbability = 60;
    private final int wrongSkillProbability = 45;
    Skill skill = new SnizhkoSkill();
    public Snizhko(String name, String sex, List<Question> questions, int id) {
        super(name, sex, questions, id);
    }

    @Override
    protected void correctStudentAnswerSkill() {

        if(Probability.eventProbability(correctSkillProbability))
            skill.studentAnswerCorrect(super.getStudent());
    }

    @Override
    protected void wrongStudentAnswerSkill() {
        if(Probability.eventProbability(wrongSkillProbability))
            skill.studentAnswerFalse(super.getStudent());
    }
}
