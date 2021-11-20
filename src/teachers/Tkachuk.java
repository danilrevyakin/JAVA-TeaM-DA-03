package teachers;

import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.TkachukSkill;

public final class Tkachuk extends Teacher{
	
	private Skill skill = new TkachukSkill();
	
	public Tkachuk(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}	

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.event_Probability(80))
			skill.studentAnswerCorrect(student, lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.event_Probability(33)) 
			skill.studentAnswerFalse(student, lastQuestion);
	}
	
}
