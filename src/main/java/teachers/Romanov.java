package teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.RomanovSkill;
import model.skill.Skill;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

public final class Romanov extends Teacher implements Serializable {
	private final int correctSkillProbability = 50;
	private final int wrongSkillProbability = 75;
	Skill skill = new RomanovSkill();
	public Romanov(String name, String sex, List<Question> questions, int id) {
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
