package model.skill;

import controller.Probability;
import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;

public class StickanovSkill implements Skill, Serializable {
	ConsoleView consoleView = new ConsoleView();
	@Override
	public void studentAnswerCorrect(Student student, Question question) {
		int health = (int)(student.getHealth() *  0.7f);
		int score = (int)(student.getHealth() *  0.7f);
		consoleView.StikanovSkillCorrectMessage(score,health);
		student.setHealth(student.getHealth() - health);
		student.setScore(student.getScore()+score);
	}

	@Override
	public void studentAnswerFalse(Student student, Question question) {
		int health = student.getHealth();
		int score = student.getScore();
		consoleView.StikanovSkillWrongMessage();
		student.setHealth(health - (int)(health * 0.15f));
		student.setScore(score - (int)(score * 0.15f));
	}
	
}
