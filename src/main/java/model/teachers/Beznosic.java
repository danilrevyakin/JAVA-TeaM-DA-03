package model.teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Teacher;

public final class Beznosic extends Teacher implements Serializable {
	private int counterOfCribbedLabs = 0;
	public Beznosic(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id,75, 75);
		manaPrice = 70;
		manaChangePrice = 50;
	}

	@Override
	protected String wrongStudentReaction(){
		counterOfCribbedLabs++;
		String parent = super.wrongStudentReaction();
		return parent + "\n" + BeznosReactionOnCribbedLab();
	}
	private String BeznosReactionOnCribbedLab(){
		if(counterOfCribbedLabs == 1){
			String message = "I guess I saw this work...";
			Question hardQuestion = new Question();
			hardQuestion.setQuestion("Have you gribed the lab?..");
			String answer = "of course not";
			hardQuestion.setChoices(new ArrayList<>(List.of("yes","no","probably", answer)));
			hardQuestion.setAnswer(answer);
			addNextQuestion(hardQuestion);
			return message;
		}
		String message = "You crib labs again and again...\n" +
				"I have to decrease you score on 40%...";
		getStudent().increaseScoreOn(-(int)(getStudent().getScore() * 0.4));
		return message;
	}

}
