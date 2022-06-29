package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;
import model.additionalServices.Probability;
import model.modes.Medium;

public final class Statckevich extends Teacher{

	public Statckevich(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 33, 50);
		manaPrice = 30;
		manaChangePrice = -5;
	}

	@Override
	protected String wrongStudentReaction() {
		if (Probability.eventProbability(75)) {
			return super.wrongStudentReaction();
		}
		this.mode = new Medium();
		manaPrice *= 1.25;
		return "I guess You are bored. Maybe I should change my mode on Medium";
	}

}
