package teachers;

import java.io.Serializable;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.modes.Hard;

public final class High extends Teacher implements Serializable {
	private final int correctSkillProbability = 35;
	private final int wrongSkillProbability = 90;
	public High(String name, String sex, List<Question> questions, int id) {
		super(name, sex, questions, id);
		mode = new Hard();
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
