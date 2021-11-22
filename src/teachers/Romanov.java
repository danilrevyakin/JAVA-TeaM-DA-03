package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.HighSkill;
import model.skill.RomanovSkill;
import model.skill.Skill;

public final class Romanov extends Teacher implements Serializable {
	private final int correctSkillProbability = 50;
	private final int wrongSkillProbability = 75;
	Skill skill = new RomanovSkill();
	public Romanov(String name, boolean sex, ArrayList<Question> questions) {
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
