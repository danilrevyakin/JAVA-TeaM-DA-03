package model.modes;

import java.io.Serializable;
import java.util.Random;

import model.Student;
import view.ConsoleView;

public class Easy implements Mode, Serializable {
	static private final Random rand = new Random();

	@Override
	public String studentAnswerCorrect(Student student) {
		int addHealth = Math.abs(rand.nextInt() % 6);
		int addMana = Math.abs(rand.nextInt() % 11);
		int addScore = 25;
		String message = "As always correct, I will pamper you with additional points!";
		student.setHealth(student.getHealth() + addHealth);
		student.setMana(student.getMana() + addMana);
		student.setScore(student.getScore()+addScore);
		return message;
	}

	@Override
	public String studentAnswerFalse(Student student) {
		int addScore = rand.nextInt() % 5; // min = -4, max = 4 - I know this
		String message = "I don't even know what grade to give you!";
		student.setScore(student.getScore() + addScore);
		return message;
	}

}
