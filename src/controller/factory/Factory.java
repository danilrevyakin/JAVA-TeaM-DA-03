package controller.factory;

import model.Pair;
import model.Question;
import model.Teacher;
import teachers.*;

import java.util.ArrayList;

import static controller.FileManager.readTeacherInfo;


public final class Factory implements TeacherFactory {

//	private final Map<String, Class> teachers = new HashMap<>();

//	public Factory() {
//		teachers.put(Artuhov.class.getName(), Artuhov.class);
//		teachers.put(Bokhonov.class.getName(), Bokhonov.class);
//		teachers.put(High.class.getName(), High.class);
//		teachers.put(Beznosic.class.getName(), Beznosic.class);
//		teachers.put(Verbitskiy.class.getName(), Verbitskiy.class);
//		teachers.put(Romanov.class.getName(), Romanov.class);
//		teachers.put(Statckevich.class.getName(), Statckevich.class);
//		teachers.put(Stickanov.class.getName(), Stickanov.class);
//		teachers.put(Tkachuk.class.getName(), Tkachuk.class);
//		teachers.put(Snizhko.class.getName(),Snizhko.class);
//	}

	@Override
	public Teacher getTeacher(String teacherName) {
		Teacher toReturn;
		Pair<Pair<String, Boolean>, ArrayList<Question>> teacherInfo = readTeacherInfo(teacherName);
		String surname = teacherInfo.getFirst().getFirst();
		Boolean sex = teacherInfo.getFirst().getSecond();
		ArrayList<Question> questions = teacherInfo.getSecond();

		toReturn = switch (teacherName) {
			case "Artuhov" -> new Artuhov(surname, sex, questions);
			case "Beznosic" -> new Beznosic(surname, sex, questions);
			case "Bokhonov" -> new Bokhonov(surname, sex, questions);
			case "High" -> new High(surname, sex, questions);
			case "Romanov" -> new Romanov(surname, sex, questions);
			case "Statckevich" -> new Statckevich(surname, sex, questions);
			case "Stickanov" -> new Stickanov(surname, sex, questions);
			case "Tkachuk" -> new Tkachuk(surname, sex, questions);
			case "Verbitskiy" -> new Verbitskiy(surname, sex, questions);
			case "Snizhko" -> new Snizhko(surname, sex, questions);
			default -> throw new IllegalArgumentException("Wrong teacher surname: " + teacherName);
		};
		return toReturn;
//		Class teacherClass = teachers.get(teacherName);
//		Object obj = null;
//		try {
//			obj = teacherClass.getDeclaredConstructor().newInstance();
//		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		return (Teacher) obj;
	}
}
