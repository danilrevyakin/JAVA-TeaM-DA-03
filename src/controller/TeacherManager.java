package controller;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TeacherManager {
	
    private Vector<String> teachers_names_list = init_names_list();
    private static final ArrayList<Question> questionSet = FileManager.initQuestions();
    public static final int NUMBER_OF_TEACHERS = 11; 
    

    private Vector<Teacher> init_teachers() {
    	Vector<Teacher> Teachers_Set = new Vector<>(teachers_names_list.size());
    	for(String name: teachers_names_list) {
			assert questionSet != null;
			Teacher newTeacher = new Teacher(name, questionSet);
    		Teachers_Set.add(newTeacher);
    	}
    	return Teachers_Set;
    }
    
    
    private Vector<String> init_names_list(){
    	try {
			return FileManager.initTeachers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }


	public Vector<Teacher> getTeachers() {
		return init_teachers();
	}
}
