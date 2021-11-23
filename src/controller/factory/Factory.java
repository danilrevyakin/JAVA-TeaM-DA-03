package controller.factory;

import model.Pair;
import model.Question;
import model.Teacher;
import teachers.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static controller.FileManager.readTeacherInfo;


public final class Factory implements TeacherFactory {

	private final Map<String, Class> teachers = new HashMap<>();

	public Factory() {
		File nf = new File("src\\teachers");
		for (File teacherFile : Objects.requireNonNull(nf.listFiles())) {
			teachers.put(teacherFile.getName(), teacherFile.getClass());
		}
	}

	@Override
	public Teacher getTeacher(String className) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Pair<Pair<String, Boolean>, ArrayList<Question>> teacherInfo = readTeacherInfo(className);
		String surname = teacherInfo.getFirst().getFirst();
		Boolean sex = teacherInfo.getFirst().getSecond();
		ArrayList<Question> questions = teacherInfo.getSecond();

		Class myClass = Class.forName("teachers."+className);
		Constructor constrt = myClass.getConstructor(String.class, boolean.class, ArrayList.class);

		return (Teacher) constrt.newInstance(surname, sex, questions);
	}
}
