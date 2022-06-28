package model.modes;

import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;


public class Hard implements Mode, Serializable {

    Random random = new Random();
    public String correctMessage(int k){
        return "I see you were preparing, +" + k + " points to your score!";
    }
    public String wrongMessage(int k){
        return ("You answer somehow uncertainty\nScore "+ k);
    }
    @Override
    public String studentAnswerCorrect(Student student) {
        int k = -2;
        student.increaseScoreOn(-k);
        return correctMessage(k);
    }

    @Override
    public String studentAnswerFalse(Student student) {
        int k = Math.abs(random.nextInt() % 10);
        student.setScore(student.getScore() - k);
        return wrongMessage(-k);
    }
}
