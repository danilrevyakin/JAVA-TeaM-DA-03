package model.skill;

import model.Question;
import model.Student;

public interface Skill{
	 void studentAnswerCorrect(Student student, Question question);
	 void studentAnswerFalse(Student student, Question question);
}
