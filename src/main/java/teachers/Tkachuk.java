package teachers;

import java.io.Serializable;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;

public final class Tkachuk extends Teacher implements Serializable {
	private final int correctSkillProbability = 80;
	private final int wrongSkillProbability = 33;

	public Tkachuk(String name, String sex, List<Question> questions, int id) {
		super(name, sex, questions, id);
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(correctSkillProbability))
			mode.studentAnswerCorrect(super.getStudent());
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(wrongSkillProbability))
			mode.studentAnswerFalse(super.getStudent());
	}
	
}
