package model.skill;

import model.Question;
import model.Student;

public final class TkachukSkill implements Skill{

	@Override
	public void studentAnswerCorrect(Student student, Question question) {
		student.score -= 5;
	}

	@Override
	public void studentAnswerFalse(Student student, Question question) {
		student.score -= 10;
	}
	
}
