package teachers;

import java.io.Serializable;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.modes.VeryHard;

public final class Stickanov extends Teacher implements Serializable {
	private final int skillProbability = 50;
	public Stickanov(String name, String sex, List<Question> questions, int id) {

		super(name, sex, questions, id);
		mode = new VeryHard();
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
