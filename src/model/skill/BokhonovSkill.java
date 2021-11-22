package model.skill;

import controller.MissionManager;
import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public class BokhonovSkill  implements Skill, Serializable {

    private final ConsoleView consoleView = new ConsoleView();

    @Override
    public void studentAnswerCorrect(Student student, Question question) {
        if(consoleView.BokhonovSkillMessage()){
            student.setScore(student.getScore()+10);
        }
        else{
            student.setHealth(student.getHealth()/2);
        }
    }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        Random random = new Random();
        int k = Math.abs(random.nextInt() % 10);
        consoleView.teacherAngry(-k);
        student.setScore(student.getScore() - k);
        student.setHealth(student.getHealth() - k);
        student.setMana(student.getMana() - k);
    }
}
