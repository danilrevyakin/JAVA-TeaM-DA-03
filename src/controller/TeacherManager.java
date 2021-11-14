package controller;
import model.*;
import view.ConsoleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TeacherManager {
	
    private static Vector<String> teachers_names_list = init_names_list();
    private static final ConsoleView consoleView = new ConsoleView();
    private static final ArrayList<Question> questionSet = FileManager.initQuestions();
    private static final Vector<Teacher> Teachers_Set = init_teachers();
    public static final int NUMBER_OF_TEACHERS = 11; 
    

    private static Vector<Teacher> init_teachers() {
    	Vector<Teacher> Teachers_Set = new Vector(teachers_names_list.size());
    	for(String name: teachers_names_list) {
    		Teacher newTeacher = new Teacher(name, (ArrayList<Question>)questionSet.clone());
    		Teachers_Set.add(newTeacher);
    	}
    	return Teachers_Set;
    }
    
    
    private static Vector<String> init_names_list(){
    	try {
			return FileManager.initTeachers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }


	public static Vector<Teacher> getTeachers() {
		return (Vector<Teacher>)Teachers_Set.clone();
	}
}
