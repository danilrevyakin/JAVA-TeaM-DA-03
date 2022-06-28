package model.teachers;

import model.Question;
import model.Teacher;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Artuhov extends Teacher implements Serializable {
	public Artuhov(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 50, 50);
		manaChangePrice = 50;
	}

	@Override
	protected String correctStudentReaction(){
		return super.correctStudentReaction() + ArtuhovReactionOnGoodRGR();
	}

	private String ArtuhovReactionOnGoodRGR(){
		int points = rand.nextInt(10) + 5;
		String message = "\nI like your work. It is so unique. I give you " + points + " additional points";
		getStudent().increaseScoreOn(points);
		return message;
	}

	@Override
	protected String wrongStudentReaction(){
		return super.wrongStudentReaction() + ArtuhovReactionOnBadGR();
	}
	private String ArtuhovReactionOnBadGR(){
		if(this.hasQuestions()){
			String message = "Oh.., Ok, I let you read my book on next question";
			Question task = this.giveNextQuestion();
			String question = task.getQuestion();
			List<String> chars = Arrays.asList(question.split(""));
			Collections.shuffle(chars);
			String newQuestion = String.join("",chars);
			newQuestion += "\nI hope you use program for circuitry to launching my book";
			task.setQuestion(newQuestion);
			givePreviousQuestion();
			return message;
		}
		return "";
	}
}
