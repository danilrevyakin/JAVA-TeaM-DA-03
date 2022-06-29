package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;
import model.modes.Medium;

public final class Verbitskiy extends Teacher{
	String repeat = "Oh, sorry, I didn't hear the question, please answer again";

	public Verbitskiy(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 70 , 70);
		mode = new Medium();
		manaPrice  = 80;
	}

	@Override
	protected String wrongStudentReaction() {
		addNextQuestion(givePreviousQuestion());
		giveNextQuestion();
		return super.wrongStudentReaction() + repeat;
	}

}
