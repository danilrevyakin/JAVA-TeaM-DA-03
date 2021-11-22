package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.BokhonovSkill;
import model.skill.Skill;

public class Bokhonov extends Teacher implements Serializable {
	private final int skillProbability = 40;
	Skill skill = new BokhonovSkill();
	public Bokhonov(String name, boolean sex, ArrayList<Question> questions) {
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
