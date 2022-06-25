package teachers;

import java.io.Serializable;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.modes.VeryHard;

public final class Tkachuk extends Teacher implements Serializable {
	public Tkachuk(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 80, 33);
		mode = new VeryHard();
	}

	@Override
	protected String wrongStudentReaction() {
		return super.correctStudentReaction() + "Everything is good. Don't worry, be happy";
	}

}
