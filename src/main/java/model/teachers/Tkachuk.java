package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;
import model.modes.VeryHard;

public final class Tkachuk extends Teacher{
	public Tkachuk(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 80, 33);
		mode = new VeryHard();
		manaPrice = 90;
	}

	@Override
	protected String wrongStudentReaction() {
		return super.wrongStudentReaction() + "\nEverything is good. Don't worry, be happy";
	}

}
