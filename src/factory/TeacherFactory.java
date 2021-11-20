package factory;

import java.util.ArrayList;

import model.Question;
import model.Teacher;

public class TeacherFactory {

	public TeacherFactory() {
		// TODO Auto-generated constructor stub
	}
	public Teacher getTeacher(String name) {
		//readTeacherInfo(String filename)
		ArrayList<Question> a = null;
		return (new Teacher("", true, a));
	}

}
