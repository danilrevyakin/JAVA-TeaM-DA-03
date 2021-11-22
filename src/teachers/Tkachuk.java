package teachers;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.TkachukSkill;

public final class Tkachuk extends Teacher implements Serializable {
	private final int correctSkillProbability = 80;
	private final int wrongSkillProbability = 33;
	private Skill skill = new TkachukSkill();
	
	public Tkachuk(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}	

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(correctSkillProbability))
			skill.studentAnswerCorrect(student, lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(wrongSkillProbability))
			skill.studentAnswerFalse(student, lastQuestion);
	}
	
}
