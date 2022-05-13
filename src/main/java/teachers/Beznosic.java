package teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.BeznosicSkill;
import model.skill.Skill;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

public final class Beznosic extends Teacher implements Serializable {
	private final int skillProbability = 75;
	Skill skill = new BeznosicSkill();
	public Beznosic(String name, String sex, List<Question> questions, int id) {
		super(name, sex, questions, id);
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
		skill.studentAnswerCorrect(super.getStudent());
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
		skill.studentAnswerFalse(super.getStudent());
	}

}
