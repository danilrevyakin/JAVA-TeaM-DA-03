package controller;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import controller.factory.TeacherFactory;
import model.Teacher;
import controller.factory.Factory;

public class TeacherManager {
	
	public static final int NUMBER_OF_TEACHERS = 10;
	private final TeacherFactory teacherFactory = new Factory();
	private final FileManager fileManager = new FileManager();
	private final ArrayList<String> names = fileManager.initSurnamesOfTeachers();
	
	private ArrayList<Teacher> initTeachers() throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return teacherList;
	}
	
}