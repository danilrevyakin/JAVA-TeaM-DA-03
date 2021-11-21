package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.HighSkill;
import model.skill.Skill;

public final class High extends Teacher implements Serializable {

	Skill skill = new HighSkill();
	public High(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {

		if(Probability.eventProbability(35))
			skill.studentAnswerCorrect(super.student, super.lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(90))
			skill.studentAnswerFalse(student, lastQuestion);
	}


}
