package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.StickanovSkill;

public final class Stickanov extends Teacher implements Serializable {
	private final int skillProbability = 50;

	private final Skill skill = new StickanovSkill();
	public Stickanov(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			skill.studentAnswerCorrect(student, lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			skill.studentAnswerFalse(student, lastQuestion);
	}
	

}