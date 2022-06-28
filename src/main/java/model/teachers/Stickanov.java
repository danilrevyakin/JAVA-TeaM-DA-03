package model.teachers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Probability;
import model.Question;
import model.Teacher;
import model.modes.VeryHard;

public final class Stickanov extends Teacher implements Serializable {
	public Stickanov(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 50, 100);
		mode = new VeryHard();
		manaPrice = 100;
		manaChangePrice = 100;
	}


	@Override
	protected void makeWorkForMana(){
		if(Probability.eventProbability(33)){
			super.makeWorkForMana();
			return;
		}
		message += "Hahaha, You thought I give you some candy?" +"" +
				"You are wrong. I have erased correct answer";
		deleteAnswer();
	}

	private void deleteAnswer(){
		Question currentQuestion = givePreviousQuestion();
		ArrayList<String> choices = currentQuestion.getChoices();
		choices.remove(currentQuestion.getAnswer());
		currentQuestion.setChoices(choices);
	}


}
