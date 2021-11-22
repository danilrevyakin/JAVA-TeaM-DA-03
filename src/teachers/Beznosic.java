package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.ArtuhovSkill;
import model.skill.BeznosicSkill;
import model.skill.Skill;

public final class Beznosic extends Teacher implements Serializable {
	private final int skillProbability = 75;
	Skill skill = new BeznosicSkill();
	public Beznosic(String name, boolean sex, ArrayList<Question> questions) {
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
