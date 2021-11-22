package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.ArtuhovSkill;
import model.skill.Skill;

public final class Artuhov extends Teacher implements Serializable {
	private final int skillProbability = 50;
	Skill skill = new ArtuhovSkill();
	public Artuhov(String name, boolean sex, ArrayList<Question> questions) {
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
