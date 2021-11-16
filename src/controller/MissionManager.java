package controller;
import model.*;
import view.*;
import java.util.Vector;

public class MissionManager {

    private final ConsoleView consoleView = new ConsoleView();
    private final TeacherManager teacherManager = new TeacherManager();
    private final StudentManager studentManager = new StudentManager();
    public static final int MAX_NUMBER_OF_MISSIONS = TeacherManager.NUMBER_OF_TEACHERS;  // Teachers are 11

     
    
    public  Mission createMission(Student student, int missionsNum, Teacher teacher){ 
        Mission newMission = new Mission(student, teacher, missionsNum);    		
        return newMission;
    }

    public void generateMissions(Student student){
    	student.missions = new Vector<>(student.getLevel() + 1);
    	Vector<Teacher> Teachers = teacherManager.getTeachers();
        for(int i = 0; i < MAX_NUMBER_OF_MISSIONS; i++){
        	student.missions.add(createMission(student, student.getLevel() + i, Teachers.get(i)));
        }
    }


    public void startMission(Student student, Mission mission){
    	mission.set_in_Progress();
    	consoleView.open(mission);
        String studentAnswer;
        Question question = mission.giveQuestion();

        
        while(question != null && mission.getTeacher().getHealth() > 0 
        		&& student.getHealth() > 0) {
        	consoleView.quiz(question);
        	studentAnswer = studentManager.giveAnswer();
            if (studentAnswer.toLowerCase().equals(question.getAnswer())) {
                //Polymorphism
                for(Person player : mission.getPeople()){
                    player.correctStudentAnswer();
                }
                
                if (mission.getTeacher().getHealth() > 0) consoleView.correctAnswerOutput(mission.getTeacher());
                else {
                	mission.setCompleted();
                }												  
             
            } 
            else {
                //Polymorphism
                for(Person player : mission.getPeople()){
                    player.wrongStudentAnswer();
                }
              
            }
            question = mission.giveQuestion();
        }
        
        if(mission.getTeacher().getHealth() < 0) {
        	mission.setCompleted();
        }
        if(mission.getTeacher().getHealth() > 0 && question == null) {
        	System.out.println("Failed");
        	mission.setFailed();
        }
        if(student.getHealth() < 0){
        	mission.setFailed();
        }
    }

    public void openMission(Student student){
        int missionNumber = 0;
        Mission mission;
        if(!student.has_available_mission()) {
        	consoleView.has_no_mission();
        	return;
        }
        do {
        	while (missionNumber <= 0 || missionNumber > student.missions.size()){
                consoleView.choosingMission(student.missions);
                missionNumber = consoleView.missinNumScanner(missionNumber);
                if(missionNumber == 1) {
                	int i = 9;
                }
            }
        	
        	mission = student.missions.get(missionNumber - 1);
        	missionNumber = -100;
        }while(!mission.mission_available());
        
        startMission(student, mission);
    }
}
