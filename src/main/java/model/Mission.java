package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mission implements Serializable {
    private final int missionNumber;
    private final int playersNumber = 2;
    private ArrayList<Person> people = new ArrayList<>(playersNumber);
    private Teacher teacher;
    private Student student;
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

    public Mission(Student student, Teacher teacher, int missionNumber) {
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        this.student = student;
        people.add(student);
        people.add(teacher);
        stateMission = State.MISSION_UNCOMPLETED;
    }

    public boolean missionAvailable() {
        if (!Objects.equals(stateMission, State.MISSION_COMPLETED) && !Objects.equals(stateMission, State.MISSION_FAILED))
            return true;
        return false;
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

    public List<Question> giveQuestion() {
        return teacher.getQuestions();
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
