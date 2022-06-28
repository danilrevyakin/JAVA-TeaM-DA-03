package controller.factory;

import controller.SQLiteJDBC;
import controller.hibernateUtil.QuestionDao;
import model.additionalServices.Pair;
import model.Teacher;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class TeachersFactory {
	
	public static final int NUMBER_OF_TEACHERS = 10;
	SQLiteJDBC jdbc = new SQLiteJDBC();

	public List<Teacher> getTeachers() {
		ArrayList<Teacher> Teachers = new ArrayList<>(NUMBER_OF_TEACHERS);
		ArrayList<String> names =jdbc.getTeachersName();
		for(String surname: names) {
			Teacher teacher = getTeacher(surname);
			Teachers.add(teacher);
		}
		return Teachers;
	}

	public Teacher getTeacher(String className){
		SQLiteJDBC jdbc = new SQLiteJDBC();
		QuestionDao questionDao = new QuestionDao();
		Pair<Integer, String> pair = jdbc.teacherInfo(className);

		Class myClass;
		Teacher t = null;
		try {
			myClass = Class.forName("model.teachers." + className.substring(3));
			Constructor constrt = myClass.getConstructor(String.class, String.class, List.class, int.class, int.class, int.class);
			String name = className.substring(3);
			String sex = pair.second();
			int id = pair.first();
			t =  (Teacher) constrt.newInstance(name, sex,
					questionDao.getTeacherQuestions(id), id, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}