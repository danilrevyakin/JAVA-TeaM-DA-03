package model.modes;

import model.Student;

public interface Mode {
	 String studentAnswerCorrect(Student student);
	 String studentAnswerFalse(Student student);
}
