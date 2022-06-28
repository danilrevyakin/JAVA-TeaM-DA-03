package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;
import model.additionalServices.Probability;
import model.modes.Hard;
import model.modes.Medium;

public final class High extends Teacher implements Serializable {
	public High(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 35, 90);
		mode = new Hard();
		manaPrice = 75;
	}

	@Override
	protected String wrongStudentReaction() {
		if (Probability.eventProbability(50)) {
			this.mode = new Medium();
			manaPrice *= 0.75;
		}else{
			this.mode = new Hard();
			manaPrice *= 1.25;
		}
		return super.wrongStudentReaction();
	}
}
