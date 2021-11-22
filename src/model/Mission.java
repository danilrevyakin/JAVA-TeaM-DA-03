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
    	MISSION_COMPLETED, MISSION_FAILED, MISSION_UNCOMPLETED;
    }
    
    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        people.add(student);
        people.add(teacher);
        stateMission = State.MISSION_UNCOMPLETED;
    }
    
    public boolean missionAvailable() {
    	if(!Objects.equals(stateMission, State.MISSION_COMPLETED) && !Objects.equals(stateMission, State.MISSION_FAILED)) return true;
    	return false;
    }
    
    public void setCompleted() {
    	stateMission = State.MISSION_COMPLETED;
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
