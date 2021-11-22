package model.skill;

import controller.MissionManager;
import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public class VerbitskiySkill implements Skill, Serializable {

    private final ConsoleView consoleView = new ConsoleView();
    Random random = new Random();
    int k = Math.abs(random.nextInt() % 15);

    @Override
    public void studentAnswerCorrect(Student student, Question question) {
        consoleView.VerbitskiySkillMessage();
        consoleView.quiz(question);
        consoleView.giveAnswer();
        consoleView.teacherHappy(k);
        int health = student.getHealth();
        student.setHealth(health + k);
        student.setScore(student.getScore() + k);
    }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        consoleView.VerbitskiySkillMessage();
        consoleView.quiz(question);
        consoleView.giveAnswer();
        consoleView.teacherAngry(k);
        int health = student.getHealth();
        student.setHealth(health - k);
        student.setScore(student.getScore() - k);
    }
}
