package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mission implements Serializable {
    private final int missionNumber;
    private final int playersNumber = 2;
    private final ArrayList<Person> people = new ArrayList<>(playersNumber);
    private final Teacher teacher;
    private final Student student;
    private State stateMission;

    public enum State {
        MISSION_COMPLETED("Mission completed"),
        MISSION_FAILED("Mission failed"),
        MISSION_UNCOMPLETED("Mission uncompleted");

        private final String normalName;

        State(String normalName) {
            this.normalName = normalName;
        }

        public String getNormalName() {
            return normalName;
        }
    }

    public State getStateMission() {
        return stateMission;
    }

    public Mission(Teacher teacher, Student student) {
        this.teacher = teacher;
        this.student = student;
        this.missionNumber = teacher.getId();
        stateMission = State.MISSION_UNCOMPLETED;
        people.add(teacher);
        people.add(student);
    }

    public boolean missionAvailable() {
        return  (!Objects.equals(stateMission, State.MISSION_COMPLETED) && !Objects.equals(stateMission, State.MISSION_FAILED));
    }

    public void setCompleted() {
        stateMission = State.MISSION_COMPLETED;
    }

    public void setFailed() {
        stateMission = State.MISSION_FAILED;
    }

    public int getMissionNumber() {
        return missionNumber;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public Student getStudent() {
        return student;
    }
}
