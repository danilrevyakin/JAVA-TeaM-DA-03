package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Mission implements Serializable{
    private int missionNumber;
    private final int playersNumber = 2;
    private ArrayList<Person> people = new ArrayList<>(playersNumber);
    private Teacher teacher;
    public static final String MISSION_COMPLETED = "c";
    public static final String MISSION_FAILED = "f";
    public static final String MISSION_IN_PROGRESS = "p";
    public static final String MISSION_UNSTARTED = "n";
    private String stateMission;
    
    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        people.add(student);
        people.add(teacher);
        stateMission = MISSION_UNSTARTED;
    }
    
    public boolean missionAvailable() {
    	if(!Objects.equals(stateMission, MISSION_COMPLETED) && !Objects.equals(stateMission, MISSION_FAILED)) return true;
    	return false;
    }
    
    //getters & setters
    public String getNameState() {
    	if(this.stateMission.equals(MISSION_COMPLETED))
    		return "COMPLETED";
    	else if(this.stateMission.equals(MISSION_FAILED)) {
    		return "FAILED";
    	}
    	else if(this.stateMission.equals(MISSION_IN_PROGRESS)) {
    		return "IN PROGRESS";
    	}
    	return "UNSTARTED";
    		
    }
    public String getState() {
    	return this.stateMission;
    }
    public void setCompleted() {
    	stateMission = MISSION_COMPLETED;
    }
    public void setInProgress() {
    	stateMission = MISSION_IN_PROGRESS;
    }
    public void setFailed() {
		stateMission = MISSION_FAILED;
	}
    public int getMissionNumber(){
        return missionNumber;
    }
    public ArrayList<Question> giveQuestion() {
    	return teacher.give_Question();
    }
    public Teacher getTeacher() {
    	return this.teacher;
    }
    public ArrayList<Person> getPeople(){return people;}

	
}
