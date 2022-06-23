package model.modes;

import java.io.Serializable;
import java.util.Random;

import model.Student;
import view.ConsoleView;

public class Easy implements Mode, Serializable {
	private Random rand = new Random();
	ConsoleView consoleView = new ConsoleView();
	
	@Override
	public void studentAnswerCorrect(Student student) {
		int addHealth = Math.abs(rand.nextInt() % 6);
		int addMana = Math.abs(rand.nextInt() % 11);
		int addScore = 25;
		consoleView.StatkevichSkillCorrectMessage(addScore, addMana, addHealth);
		student.setHealth(student.getHealth() + addHealth);
		student.setMana(student.getMana() + addMana);
		student.setScore(student.getScore()+addScore);
	}

	@Override
	public void studentAnswerFalse(Student student) {
		int addScore = rand.nextInt() % 5; // min = -4, max = 4 - I know this
		consoleView.StatkevichSkillWrongMessage(addScore);
		student.setScore(student.getScore() + addScore);
	}

	

	

}
