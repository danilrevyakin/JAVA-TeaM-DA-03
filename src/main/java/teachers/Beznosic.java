package teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Teacher;

public final class Beznosic extends Teacher implements Serializable {
	public Beznosic(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id,75, 75);
	}

	@Override
	protected String wrongStudentReaction(){
		return super.wrongStudentReaction() + BeznosReactionOnCribbedLab();
	}
	private String BeznosReactionOnCribbedLab(){
		String message = "I guess I saw this work...";
		Question hardQuestion = new Question();
		hardQuestion.setQuestion("Have you gribed the lab?..");
		String answer = "of course not";
		hardQuestion.setChoices(new ArrayList<>(List.of("yes","no","probably", answer)));
		hardQuestion.setAnswer(answer);
		addNextQuestion(hardQuestion);
		return message;
	}

}
