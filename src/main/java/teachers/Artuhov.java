package teachers;

import controller.Probability;
import model.Question;
import model.Teacher;

import java.io.Serializable;
import java.util.List;

public final class Artuhov extends Teacher implements Serializable {
	private final int skillProbability = 50;
	public Artuhov(String name, String sex, List<Question> questions, int id) {
		super(name, sex, questions, id);
	}

	@Override
	protected void correctStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			mode.studentAnswerCorrect(super.getStudent());
	}

	@Override
	protected void wrongStudentAnswerSkill() {
		if(Probability.eventProbability(skillProbability))
			mode.studentAnswerFalse(super.getStudent());
	}


}
