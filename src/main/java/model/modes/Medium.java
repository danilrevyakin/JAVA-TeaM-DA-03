package model.modes;

import model.Student;
import java.util.Random;

public class Medium implements Mode {

    Random random = new Random();

    @Override
    public String studentAnswerCorrect(Student student) {
        int k = Math.abs(random.nextInt() % 15);
        int health = student.getHealth();
        student.setHealth(health + k);
        student.setScore(student.getScore() + k);
        return teacherHappy(k);
    }

    public String teacherHappy(int k){
        return ("I am in a good mood today, I will add a " + k + " points for you!");
    }

    public String teacherAngry(){
        return ("Omg, very stupid mistake, you pissed me off, I will give you a bad grade!");
    }

    @Override
    public String studentAnswerFalse(Student student) {
        int k = Math.abs(random.nextInt() % 15);
        int health = student.getHealth();
        student.setHealth(health - k);
        student.setScore(student.getScore() - k);
        return teacherAngry();
    }
}
