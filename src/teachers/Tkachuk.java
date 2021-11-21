package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.TkachukSkill;

public final class Tkachuk extends Teacher implements Serializable {
	
	private Skill skill = new TkachukSkill();
	
	public Tkachuk(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}	

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(80))
			skill.studentAnswerCorrect(student, lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(33))
			skill.studentAnswerFalse(student, lastQuestion);
	}
	
}
