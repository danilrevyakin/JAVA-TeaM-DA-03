package controller.factory;

import controller.SQLiteJDBC;
import hibernateUtil.QuestionDao;
import model.Pair;
import model.Teacher;

import java.lang.reflect.Constructor;
import java.util.List;


public final class Factory implements TeacherFactory {

	@Override
	public Teacher getTeacher(String className){
		SQLiteJDBC jdbc = new SQLiteJDBC();
		QuestionDao questionDao = new QuestionDao();
		Pair<Integer, String> pair = jdbc.teacherInfo(className);

		Class myClass;
		Teacher t = null;
		try {
			myClass = Class.forName("teachers." + className.substring(3));
			Constructor constrt = myClass.getConstructor(String.class, String.class, List.class, int.class, int.class, int.class);
			String name = className.substring(3);
			String sex = pair.getSecond();
			int id = pair.getFirst();
			t =  (Teacher) constrt.newInstance(name, sex,
					questionDao.getTeacherQuestions(id), id, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}