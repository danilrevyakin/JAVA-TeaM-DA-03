package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.StatckevichSkill;

public final class Statckevich extends Teacher implements Serializable {
	private final int correctSkillProbability = 33;
	private final int wrongSkillProbability = 50;
	Skill skill = new StatckevichSkill();
	
	public Statckevich(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
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
