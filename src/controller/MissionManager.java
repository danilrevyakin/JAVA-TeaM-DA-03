package controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import model.Mission;
import model.Person;
import model.Question;
import model.Student;
import model.Teacher;
import view.ConsoleView;

public class MissionManager {

    private final ConsoleView consoleView = new ConsoleView();
    private final TeacherManager teacherManager = new TeacherManager();
    private final StudentManager studentManager = new StudentManager();
    public static final int MAX_NUMBER_OF_MISSIONS = TeacherManager.NUMBER_OF_TEACHERS;  // Teachers are 10

    private  Mission createMission(Student student, int missionsNum, Teacher teacher){ 
        Mission newMission = new Mission(student, teacher, missionsNum);
        return newMission;
    }

    public void generateMissions(Student student){
    	student.missions = new Vector<>();
       ArrayList<Teacher> Teachers = teacherManager.getTeachers();
        for(int i = 0; i < MAX_NUMBER_OF_MISSIONS; i++){
            student.missions.add(createMission(student, student.getLevel() + i, Teachers.get(i)));
        }
    }

    private void playMission(Student student, Mission mission) {
    	String studentAnswer;
        ArrayList<Question> questions = mission.giveQuestion();
        Teacher teacher = mission.getTeacher();
        teacher.setStudent(student);
        for(Question question : questions) {
            if (question != null && mission.getTeacher().getHealth() > 0
                    && student.getHealth() > 0) {
                consoleView.quiz(question);
                teacher.setLastQuestion(question);
                studentAnswer = studentManager.giveAnswer();

                if (studentAnswer.toLowerCase().equals(question.getAnswer().toLowerCase())) {
                    //Polymorphism
                    for (Person player : mission.getPeople()) {
                        player.correctStudentAnswer();
                    }

                    if (mission.getTeacher().getHealth() > 0) consoleView.correctAnswerOutput(mission.getTeacher());
                    else if (mission.getTeacher().getHealth() <= 0) {
                        return;
                    }
                } 
                else {
                    //Polymorphism
                    for (Person player : mission.getPeople()) {
                        player.wrongStudentAnswer();
                    }
                }
            }
            else
            	break;
        }
    }
    
    private void setResultMission(Student student, Mission mission) {
    	if (mission.getTeacher().getHealth() <= 0) {
            mission.setCompleted();
            student.decrease_Counter_availableMissions();
        }
        else if (mission.getTeacher().getHealth() > 0) {
            this.consoleView.YouAlmostWon();
        }
        else if (student.getHealth() < 0) {
            mission.setFailed();
            student.decrease_Counter_availableMissions();
        }
    }
    
    private void startMission(Student student, Mission mission){
        if(!mission.mission_available()){
            consoleView.missionCompleted();
            return;
        }
    	mission.set_in_Progress();
    	consoleView.open(mission);
        playMission(student, mission);
        setResultMission(student, mission);
    }

    public void openMission(Student student){
        int missionNumber = -100;
        final int EXIT = 0;
        Mission mission;
        if(!student.has_available_mission()) {
        	consoleView.has_no_mission();
        	return;
        }
        do {
        	while (missionNumber < 0 || missionNumber > student.missions.size()){
                consoleView.choosingMission(student.missions);
                missionNumber = consoleView.missinNumScanner(missionNumber);
                if(missionNumber == EXIT) {
                	return;
                }
            }
        	mission = student.missions.get(missionNumber - 1);
        }while(!mission.mission_available());
        
        startMission(student, mission);
    }
}
