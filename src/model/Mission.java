package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Mission implements Serializable{
    private int missionNumber;
    private final int playersNumber = 2;
    private ArrayList<Person> people = new ArrayList<>(playersNumber);
    private Teacher teacher;
    private State stateMission;
    
    static public enum State{
    	MISSION_COMPLETED, MISSION_FAILED, MISSION_IN_PROGRESS, MISSION_UNSTARTED;
    }
    
    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        people.add(student);
        people.add(teacher);
        stateMission = State.MISSION_UNSTARTED;
    }
    
    public boolean missionAvailable() {
    	if(!Objects.equals(stateMission, State.MISSION_COMPLETED) && !Objects.equals(stateMission, State.MISSION_FAILED)) return true;
    	return false;
    }
    
    //getters & setters
    public String getNameState() {
    	if(this.stateMission.equals(State.MISSION_COMPLETED))
    		return "COMPLETED";
    	else if(this.stateMission.equals(State.MISSION_FAILED)) {
    		return "FAILED";
    	}
    	else if(this.stateMission.equals(State.MISSION_IN_PROGRESS)) {
    		return "IN PROGRESS";
    	}
    	return "UNSTARTED";
    		
    }
    public State getState() {
    	return this.stateMission;
    }
    public void setCompleted() {
    	stateMission = State.MISSION_COMPLETED;
    }
    public void setInProgress() {
    	stateMission = State.MISSION_IN_PROGRESS;
    }
    public void setFailed() {
		stateMission = State.MISSION_FAILED;
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
