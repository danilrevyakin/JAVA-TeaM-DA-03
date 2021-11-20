package model.skill;

import java.util.Random;

import model.Question;
import model.Student;

public class StatckevichSkill implements Skill{
	private Random rand = new Random();
	
	@Override
	public void studentAnswerCorrect(Student student, Question question) {
		int add_health = rand.nextInt() % 6;
		add_health = Math.abs(add_health);
		int add_mana = Math.abs(rand.nextInt() % 11);
		student.setHealth(student.getHealth() + add_health);
		student.setMana(student.getMana() + add_mana);		
		student.score += 25;
	}

	@Override
	public void studentAnswerFalse(Student student, Question question) {
		int add_score = rand.nextInt() % 5; // min = -4, max = 4 - I know this
		student.setScore(student.getScore() + add_score);
	}

	

	

}
