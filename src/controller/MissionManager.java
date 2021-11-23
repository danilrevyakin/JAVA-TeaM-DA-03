package controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import model.Mission;
import model.Person;
import model.Question;
import model.Student;
import model.Teacher;
import view.ConsoleView;

public class MissionManager{

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
            student.missions.add(createMission(student, (1 + i), Teachers.get(i)));
        }
    }
    
    public void openMission(Student student){
    	final int unAvailableMission = -100;
        int missionNumber = unAvailableMission;
        final int EXIT = 0;
        Mission mission;
        if(!student.hasAvailableMission()) {
        	consoleView.hasNoMission();
        	return;
        }
        for(;;) {
        	while (missionNumber < 0 || missionNumber > student.missions.size()){
                consoleView.choosingMission(student.missions);
                missionNumber = consoleView.missinNumScanner(missionNumber);
                if(missionNumber == EXIT) {
                	return;
                }
            }
        	mission = student.missions.get(missionNumber - 1);
        	if(mission.missionAvailable())
        		break;
        	else missionNumber = unAvailableMission;
        }     
        startMission(student, mission);
    }
    
    private void startMission(Student student, Mission mission){
        if(!mission.missionAvailable()){
            consoleView.missionCompleted();
            return;
        }
    	consoleView.open(mission);
        playMission(student, mission);
        setResultMission(student, mission);
    }
    
    public void playMission(Student student, Mission mission) {
        student.setCurrentMission(mission);
    	String studentAnswer;
        ArrayList<Question> questions = mission.giveQuestion();
        mission.getTeacher().setStudent(student);
        for(Question question : questions) {
            if (question != null && mission.getTeacher().getHealth() > 0
                    && student.getHealth() > 0) {
            	studentAnswer = studentDoTask(mission, question);
                if (studentAnswer.equalsIgnoreCase(question.getAnswer()))
                	correctAnswer(mission);
                else wrongAnswer(mission);
            }
            else
            	break;
        }
    }
    
    private String studentDoTask(Mission mission, Question question) {
    	consoleView.quiz(question);
        mission.getTeacher().setLastQuestion(question);
        return studentManager.giveAnswer();
    }
    
    private void wrongAnswer(Mission mission) {
    	for (Person player : mission.getPeople()) {
            player.wrongStudentAnswer();
        }
    }
    
    private void correctAnswer(Mission mission) {
        for (Person player : mission.getPeople()) {
            player.correctStudentAnswer();
        }
    }
    
    private void setResultMission(Student student, Mission mission) {
    	if (mission.getTeacher().getHealth() <= 0) {
            mission.setCompleted();
            student.decreaseCounterAvailableMissions();
        }
        else if (mission.getTeacher().getHealth() > 0) {
            this.consoleView.YouAlmostWon();
        }
        else if (student.getHealth() < 0) {
            mission.setFailed();
            student.decreaseCounterAvailableMissions();
        }
    }
    
    

    
}
