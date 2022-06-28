package controller;

import model.*;

public class MissionManager {


    public void analiseResult(String studentAnswer, Question question, Mission mission) {
        String realAnswer = question.getAnswer();
        if (studentAnswer.equalsIgnoreCase(realAnswer)) {
            correctAnswer(mission);
        }else {
            wrongAnswer(mission);
        }
        setResultMission(mission.getStudent(), mission);
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
            student.setHealth(Student.MAX_HEALTH / 2);
            student.decreaseLevelOn(1);
            return;
        } else if (mission.getTeacher().hasQuestions()) {
            return;
        }
        setMissionSuccessfulState(student, mission);
    }

    private void setMissionFailedState(Student student, Mission mission) {
        mission.setFailed();
        student.decreaseCounterAvailableMissions();
        student.availableMissions.get(mission.getMissionNumber()).setFailed();
    }

    private void setMissionSuccessfulState(Student student, Mission mission) {
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
