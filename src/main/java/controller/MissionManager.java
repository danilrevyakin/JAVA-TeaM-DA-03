package controller;

import model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class MissionManager {
    private final TeacherManager teacherManager = new TeacherManager();
    public static final int MAX_NUMBER_OF_MISSIONS = TeacherManager.NUMBER_OF_TEACHERS;  // Teachers are 10

    private Mission createMission(Teacher teacher, Student student){
        return new Mission(teacher, student);
    }

    public void generateMissions(Student student){
        student.availableMissions = new HashMap<>();
        List<Teacher> teachers = teacherManager.getTeachers();
        List<String> completed;
        if (student.getPlayer().getCompletedMissions() != null) {
            completed = Arrays.stream(student.getPlayer().getCompletedMissions().split(",")).toList();
            IntStream.range(0, MAX_NUMBER_OF_MISSIONS)
                    .forEach(index -> {
                                student.availableMissions.put(index + 1, createMission(teachers.get(index), student));
                                if (completed.contains(String.valueOf(index + 1))) {
                                    student.availableMissions.get(index + 1).setCompleted();
                                }
                            }
                    );
        }else{
            IntStream.range(0, MAX_NUMBER_OF_MISSIONS)
                    .forEach(index -> student.availableMissions.put(index + 1, createMission(teachers.get(index), student)));
        }


    }

    public enum AnswerResult{
        CorrectAnswer, WrongAnswer
    }

    public AnswerResult analiseResult(String studentAnswer, Question question, Mission mission){
        String realAnswer = question.getAnswer();
        if (studentAnswer.equalsIgnoreCase(realAnswer)){
            correctAnswer(mission);
            setResultMission(mission.getStudent(), mission);
            return AnswerResult.CorrectAnswer;
        }
        wrongAnswer(mission);
        setResultMission(mission.getStudent(), mission);
        return AnswerResult.WrongAnswer;
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
        if (student.getHealth() <= 0) {
            setMissionFailedState(student, mission);
            return;
        }else if(mission.getTeacher().hasQuestions()){
            return;
        }
        setMissionSuccessfulState(student, mission);
    }

    private void setMissionFailedState(Student student, Mission mission){
        mission.setFailed();
        student.decreaseCounterAvailableMissions();
        student.availableMissions.get(mission.getMissionNumber()).setFailed();
    }

    private void setMissionSuccessfulState(Student student, Mission mission){
        mission.setCompleted();
        student.availableMissions.get(mission.getMissionNumber()).setCompleted();
        student.getPlayer().setCompletedMissions(
                student.getPlayer().getCompletedMissions()
                        + mission.getTeacher().getId()
                        + ","
        );
        student.getPlayer().setCompletedMissions(
                student.getPlayer().getCompletedMissions().replace("null", ""));
        student.decreaseCounterAvailableMissions();
    }

}
