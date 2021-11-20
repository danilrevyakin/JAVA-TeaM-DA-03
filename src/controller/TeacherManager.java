package controller;
import java.util.ArrayList;

import factory.TeacherFactory;
import model.Teacher;

public class TeacherManager {
	
	public static final int NUMBER_OF_TEACHERS = 10;
	private final TeacherFactory teacherFactory = new TeacherFactory();
	private final FileManager fileManager = new FileManager();
	
	private ArrayList<Teacher> init_teachers() {
		ArrayList<String> names = fileManager.SurnamesOfTeachers;
		ArrayList<Teacher> Teachers = new ArrayList<Teacher>(NUMBER_OF_TEACHERS);
		for(String surname: names) {
			Teacher teacher = teacherFactory.getTeacher(surname);
			Teachers.add(teacher);
		}
		return Teachers;
	}

	public ArrayList<Teacher> getTeachers() {
		return init_teachers();
	}
	
}