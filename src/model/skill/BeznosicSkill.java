package model.skill;

import controller.MissionManager;
import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public class BeznosicSkill implements Skill, Serializable {

    private final ConsoleView consoleView = new ConsoleView();
    private final MissionManager missionManager = new MissionManager();

    private final String message = "Do you want to correct the grade? ";
    private final String correctAnsw = "Ok, then start from the beginning!!";
    private final String wrongAnsw = "You are very lazy";

    @Override
    public void studentAnswerCorrect(Student student, Question question) {
        Random random = new Random();
        int k = Math.abs(random.nextInt() % 10);
        consoleView.teacherHappy(k);
        int health = student.getHealth();
        student.setHealth(health + k);
        student.setScore(student.getScore() + k);
    }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        if(consoleView.TeacherSkillMessage(message, correctAnsw, wrongAnsw)){
            missionManager.playMission(student, student.getCurrentMission());
        }
    }
}
