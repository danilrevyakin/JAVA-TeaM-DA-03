package controller;
import java.io.Serializable;
import java.util.ArrayList;
import controller.factory.TeacherFactory;
import model.Teacher;
import controller.factory.Factory;

public class TeacherManager {
	
	public static final int NUMBER_OF_TEACHERS = 10;
	private final TeacherFactory teacherFactory = new Factory();
	private final FileManager fileManager = new FileManager();
	
	private ArrayList<Teacher> initTeachers() throws InstantiationException, IllegalAccessException {
		ArrayList<String> names = fileManager.SurnamesOfTeachers;
		ArrayList<Teacher> Teachers = new ArrayList<Teacher>(NUMBER_OF_TEACHERS);
		for(String surname: names) {
			Teacher teacher = teacherFactory.getTeacher(surname);
			Teachers.add(teacher);
		}
		return Teachers;
	}

	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> teacherList = null;
		try {
			 teacherList = initTeachers();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return teacherList;
	}
	
}