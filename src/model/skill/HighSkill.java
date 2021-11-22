package model.skill;

import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;


public class HighSkill implements Skill, Serializable {

    private final ConsoleView consoleView = new ConsoleView();
    Random random = new Random();

    @Override
    public void studentAnswerCorrect(Student student, Question question) {
        int k = -2;
        consoleView.HighSkillCorrectMessage(k);
        student.setScore(student.getScore()+k);;
    }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        int k = Math.abs(random.nextInt() % 10);
        consoleView.HighSkillWrongMessage(-k);
        student.setScore(student.getScore()+ k);
    }
}
