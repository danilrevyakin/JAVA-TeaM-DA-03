package teachers;

import java.io.Serializable;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;

public final class Statckevich extends Teacher implements Serializable {
	private final int correctSkillProbability = 33;
	private final int wrongSkillProbability = 50;

	public Statckevich(String name, String sex, List<Question> questions, int id) {
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
			mode.studentAnswerFalse(getStudent());
	}

}
