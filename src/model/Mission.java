package model;

import controller.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Mission implements Serializable{
    private int missionNumber;
    private final int playersNumber = 2;
    private ArrayList<Person> People = new ArrayList<>(playersNumber);
    private Teacher teacher;
    public static final String MISSION_COMPLETED = "c";
    public static final String MISSION_FAILED = "f";
    public static final String MISSION_IN_PROGRESS = "p";
    public static final String MISSION_NOT_OPEN = "n";
    private String stateMission = MISSION_NOT_OPEN;
    
    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        People.add(student);
        People.add(teacher);
    }
    
    public boolean mission_available() {
    	if(stateMission != MISSION_COMPLETED && stateMission != MISSION_FAILED) return true; 		
    	return false;
    }
    
    //getters & setters
    public String getState() {
    	return stateMission;
    }
    public void setCompleted() {
    	stateMission = MISSION_COMPLETED;
    }
    public void set_in_Progress() {
    	stateMission = MISSION_IN_PROGRESS;
    }
    public void setFailed() {
		stateMission = MISSION_FAILED;
	}
    public int getMissionNumber(){
        return missionNumber;
    }
    public Question giveQuestion() {
    	return teacher.give_Question();
    }
    public Teacher getTeacher() {
    	return this.teacher;
    }
    public ArrayList<Person> getPeople(){return People;}

	
}
