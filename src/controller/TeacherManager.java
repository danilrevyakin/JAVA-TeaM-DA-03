package controller;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TeacherManager {

	//    private Vector<String> teachers_names_list = init_names_list();
	
	public static final int NUMBER_OF_TEACHERS = 10;


	private Vector<Teacher> init_teachers() {
		ArrayList<String> names;
		Vector<Teacher> Teachers = new Vector<Teacher>(NUMBER_OF_TEACHERS);
		FileManager.readTeacherInfo("Beznosic");
		for(String name: names) {
			Teacher teacher = magicMethod(name);
			Teachers.add(teacher);
		}
		
		return Teachers;
	}

	public Vector<Teacher> getTeachers() {
		return init_teachers();
	}
	
}