package model.skill;

import model.Question;
import model.Student;

public interface Skill {
	public void studentAnswerCorrect(Student student, Question question);
	public void studentAnswerFalse(Student student, Question question);
}
