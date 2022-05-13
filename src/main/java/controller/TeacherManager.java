package controller;

import controller.factory.Factory;
import controller.factory.TeacherFactory;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherManager {
	
	public static final int NUMBER_OF_TEACHERS = 10;
	private final TeacherFactory teacherFactory = new Factory();
	SQLiteJDBC jdbc = new SQLiteJDBC();

	public List<Teacher> getTeachers() {
		ArrayList<Teacher> Teachers = new ArrayList<>(NUMBER_OF_TEACHERS);
		ArrayList<String> names =jdbc.getTeachersName();
		for(String surname: names) {
			Teacher teacher = teacherFactory.getTeacher(surname);
			Teachers.add(teacher);
		}
		return Teachers;
	}
}