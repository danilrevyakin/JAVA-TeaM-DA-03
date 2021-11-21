package model.skill;

import controller.Probability;
import model.Question;
import model.Student;

import java.io.Serializable;

public class StickanovSkill implements Skill, Serializable {

	@Override
	public void studentAnswerCorrect(Student student, Question question) {
		int health = student.getHealth();
		student.setHealth(health - (int)(health * 0.7f));
		student.score += 15;
	}

	@Override
	public void studentAnswerFalse(Student student, Question question) {
		int health = student.getHealth();
		student.setHealth(health - (int)(health * 0.15f));
		student.score -= 20;
	}
	
}
