package controller;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TeacherManager {

	//    private Vector<String> teachers_names_list = init_names_list();
	private static final HashMap<String, ArrayList<Question>> questionSet = FileManager.initQuestions();
	public static final int NUMBER_OF_TEACHERS = 10;


	private Vector<Teacher> init_teachers() {
		Vector<Teacher> Teachers_Set = new Vector<Teacher>(NUMBER_OF_TEACHERS);
		for(Map.Entry<String, ArrayList<Question>> pair: questionSet.entrySet()) {
			assert questionSet != null;
			Teacher newTeacher = new Teacher(pair.getKey(), pair.getValue());
			Teachers_Set.add(newTeacher);
		}
		return Teachers_Set;
	}


//    private Vector<String> init_names_list(){
//    	try {
//			return FileManager.initTeachers();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return null;
//    }


	public Vector<Teacher> getTeachers() {
		return init_teachers();
	}
}