package model.modes;

import model.Student;
import view.ConsoleView;

import java.io.Serializable;

public class VeryHard implements Mode, Serializable {

	@Override
	public String studentAnswerCorrect(Student student) {
		int health = (int)(student.getHealth() *  0.7f);
		int score = (int)(student.getHealth() *  0.7f);
		student.setHealth(student.getHealth() - health);
		student.setScore(student.getScore()+score);
		return "You are unsure of the answer?";
	}

	@Override
	public String studentAnswerFalse(Student student) {
		int health = student.getHealth();
		int score = student.getScore();
		student.setHealth(health - (int)(health * 0.15f));
		student.setScore(score - (int)(score * 0.15f));
		return "Professionals don't answer that way\nReduce your health and points by 15%";
	}
	
}
