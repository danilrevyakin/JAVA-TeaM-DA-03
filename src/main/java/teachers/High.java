package teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.modes.Hard;

public final class High extends Teacher implements Serializable {
	public High(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 35, 90);
		mode = new Hard();
	}
}
