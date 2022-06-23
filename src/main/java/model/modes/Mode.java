package model.modes;

import model.Student;

public interface Mode {
	 void studentAnswerCorrect(Student student);
	 void studentAnswerFalse(Student student);
}
