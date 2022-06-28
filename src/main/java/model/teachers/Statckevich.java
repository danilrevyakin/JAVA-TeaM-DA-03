package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;

public final class Statckevich extends Teacher implements Serializable {

	public Statckevich(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 33, 50);
		manaPrice = 30;
		manaChangePrice = -5;
	}


}
