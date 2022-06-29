package model.teachers;

import java.io.Serializable;
import java.util.List;

import model.Question;
import model.Teacher;

public final class Romanov extends Teacher {
	private final int manaDifference = 20;

	public Romanov(String name, String sex, List<Question> questions, int id, int correctSkillProbability, int wrongSkillProbability) {
		super(name, sex, questions, id, 50, 75);
		manaPrice = rand.nextInt(30) + 40;
	}

	public String RomanovLikeYourSpeech(int k){
		return ("Add " + k + " idinichock to your score!");
	}
	public String RomanovDontLikeYourSpeech(){
		return ("Lowering your mana by a couple of dozen");
	}

	@Override
	protected String correctStudentReaction() {
		int k = rand.nextInt(manaDifference);
		getStudent().increaseManaOn(k);
		return super.correctStudentReaction() + RomanovLikeYourSpeech(k);
	}

	@Override
	protected String wrongStudentReaction() {
		int k = rand.nextInt(manaDifference);
		getStudent().increaseManaOn(-k);
		return super.wrongStudentReaction() + RomanovDontLikeYourSpeech();
	}

}
