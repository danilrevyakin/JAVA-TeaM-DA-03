package model.modes;

import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public class Medium implements Mode, Serializable {

    private final ConsoleView consoleView = new ConsoleView();
    Random random = new Random();
    int k = Math.abs(random.nextInt() % 15);

    @Override
    public void studentAnswerCorrect(Student student) {
        consoleView.teacherHappy(k);
        int health = student.getHealth();
        student.setHealth(health + k);
        student.setScore(student.getScore() + k);
    }

    @Override
    public void studentAnswerFalse(Student student) {
        consoleView.teacherAngry(k);
        int health = student.getHealth();
        student.setHealth(health - k);
        student.setScore(student.getScore() - k);
    }
}
