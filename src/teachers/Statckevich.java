package teachers;

import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.StatckevichSkill;

public final class Statckevich extends Teacher{
	
	Skill skill = new StatckevichSkill();
	
	public Statckevich(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
	}

	@Override
	protected void correctStudentAnswerSkill() {
		
		if(Probability.event_Probability(33))
			skill.studentAnswerCorrect(super.student, super.lastQuestion);	
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.event_Probability(50))
			skill.studentAnswerFalse(student, lastQuestion);
	}

}
