package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.VerbitskiySkill;

public final class Verbitskiy extends Teacher implements Serializable {
	private final int skillProbability = 50;
	Skill skill = new VerbitskiySkill();
	public Verbitskiy(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
		skill.studentAnswerCorrect(super.student, super.lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
		skill.studentAnswerFalse(super.student, super.lastQuestion);
	}

}
