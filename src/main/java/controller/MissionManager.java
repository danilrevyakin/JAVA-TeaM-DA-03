package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.ConsoleView;
import view.exam.ExamController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissionManager {

    private final ConsoleView consoleView = new ConsoleView();
    private final TeacherManager teacherManager = new TeacherManager();
    private final StudentManager studentManager = new StudentManager();
    public static final int MAX_NUMBER_OF_MISSIONS = TeacherManager.NUMBER_OF_TEACHERS;  // Teachers are 10
    private Mission mission;
    private Mission createMission(Student student, int missionsNum, Teacher teacher) {
        return new Mission(student, teacher, missionsNum);
    }
    public enum AnswerResult{
        CorrectAnswer, WrongAnswer
    }
    public void generateMissions(Student student) {
        student.missions = new ArrayList<>();
        List<Teacher> Teachers = teacherManager.getTeachers();
        for (int i = 0; i < MAX_NUMBER_OF_MISSIONS; i++) {
            student.missions.add(createMission(student, (1 + i), Teachers.get(i)));
        }
    }

    public void openMission(Student student) {
        final int unAvailableMission = -100;
        int missionNumber = unAvailableMission;
        final int EXIT = 0;

        if (!student.hasAvailableMission()) {
            consoleView.hasNoMission();
            return;
        }
        for (; ; ) {
//        	while (missionNumber < 0 || missionNumber > student.missions.size()){
//                consoleView.choosingMission(student.missions);
//                missionNumber = consoleView.missinNumScanner(missionNumber);
//                if(missionNumber == EXIT) {
//                	return;
//                }
//            }
            missionNumber = (new Random()).nextInt(10) + 1;
            mission = student.missions.get(missionNumber - 1);
            if (mission.missionAvailable())
                break;
            else missionNumber = unAvailableMission;
        }
        startMission(student, mission);
    }

    private void startMission(Student student, Mission mission) {
        if (!mission.missionAvailable()) {
            consoleView.missionCompleted();
            return;
        }
        consoleView.open(mission);
        playMission(student, mission);
    }

    public void playMission(Student student, Mission mission) {
//        student.setCurrentMission(mission);
//        String studentAnswer;
//        List<Question> questions = mission.giveQuestion();
//        for(Question question : questions) {
//
//            else
//            	break;
//        }
        mission.getTeacher().setStudent(student);
        playMissionInGUI(student, mission);
    }

    public AnswerResult analiseResult(String studentAnswer, Question question){
        studentAnswer = studentAnswer.replaceAll(" ", "");
        String realAnswer = question.getAnswer().replaceAll(" ", "");
        if (studentAnswer.equalsIgnoreCase(realAnswer)){
            correctAnswer(mission);
            setResultMission(mission.getStudent(), mission);
            return AnswerResult.CorrectAnswer;
        }
        wrongAnswer(mission);
        setResultMission(mission.getStudent(), mission);
        return AnswerResult.WrongAnswer;
    }

    private void playMissionInGUI(Student student, Mission mission){
        URL url = ExamController.class.getResource("Exam.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(controllerClass -> new ExamController(student, mission.getTeacher(), this));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private String studentDoTask(Question question) {
        consoleView.quiz(question);
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
        if (student.getHealth() < 0) {
            setMissionFailedState(student);
            return;
        }else if(mission.getTeacher().hasQuestions()){
            return;
        }
        setMissionSuccessfulState(student);
    }

    private void setMissionFailedState(Student student){
        mission.setFailed();
        student.decreaseCounterAvailableMissions();
    }

    private void setMissionSuccessfulState(Student student){
        mission.setCompleted();
        student.getPlayer().setCompletedMissions(
                student.getPlayer().getCompletedMissions()
                        + mission.getTeacher().getId()
                        + ","
        );
        student.getPlayer().setCompletedMissions(
                student.getPlayer().getCompletedMissions().replace("null", ""));
        student.decreaseCounterAvailableMissions();
    }

    public Mission getMission() {
        return mission;
    }
}
