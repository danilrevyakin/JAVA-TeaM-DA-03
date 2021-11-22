package model.skill;

import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public final class TkachukSkill implements Skill, Serializable {
	ConsoleView consoleView = new ConsoleView();
	Random rand = new Random();
	int k = Math.abs(rand.nextInt() % 5);
	@Override
	public void studentAnswerCorrect(Student student, Question question) {
		consoleView.teacherHappy(k);
		student.setScore(student.getScore()+k);
	}

	@Override
	public void studentAnswerFalse(Student student, Question question) {
		consoleView.teacherAngry(k);
		student.setScore(student.getScore()-k);
	}
	
}
