package controller;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Student;
import view.ConsoleView;

public class StudentManager  {

    private final ConsoleView consoleView = new ConsoleView();

    public Student createStudent(){
        return new Student(consoleView.setStudentName(), consoleView.setStudentSex());
    }

    public String giveAnswer(){
        return consoleView.giveAnswer();
    }
    
    public void sortStudents(List<Student> Students) {
    	Collections.sort(Students, new Comparator<Student>() {
    	    @Override
    	    public int compare(Student first, Student second) {
    	        if(first.getLevel() != second.getLevel()) {
    	        	if(first.getLevel() > second.getLevel())
    	        		return -1;
    	        	else
    	        		return 1;
    	        }
    	        if(first.getScore() != second.getScore()) {
    	        	if(first.getScore() > second.getScore())
    	        		return -1;
    	        	else
    	        		return 1;
    	        }
    	        if(first.getMana() != second.getMana()) {
    	        	if(first.getScore() > second.getScore()) 
    	        		return -1;
    	        	else
    	        		return 1;
    	        }
    	        if(first.getHealth() != second.getHealth()) {
    	        	if(first.getHealth() > second.getHealth())
    	        		return -1;
    	        	else
    	        		return 1;
    	        }
    	        return 0;
    	    }
    	});
    }
}
