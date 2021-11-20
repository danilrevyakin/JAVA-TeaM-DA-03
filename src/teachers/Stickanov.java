package teachers;

import java.util.ArrayList;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.skill.Skill;
import model.skill.StickanovSkill;

public final class Stickanov extends Teacher{
	
	private Skill skill = new StickanovSkill();
	
	public Stickanov(String name, boolean sex, ArrayList<Question> questions) {
		super(name, sex, questions);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.event_Probability(50))
			skill.studentAnswerCorrect(student, lastQuestion);
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		skill.studentAnswerFalse(student, lastQuestion);
	}
	

}
