package model.skill;

import model.Question;
import model.Student;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Random;

public class RomanovSkill implements Skill, Serializable {
    Random random = new Random();
    ConsoleView consoleView = new ConsoleView();

    @Override
    public void studentAnswerCorrect(Student student, Question question) {
        int k = Math.abs(random.nextInt() % 5);
        consoleView.RomanovSkillCorrectMessage(k);
        student.setScore(student.getScore()+ k);
    }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        int k = 10* Math.abs(random.nextInt() % 2);
        consoleView.RomanovSkillWrongMessage(-k);
        student.setMana(student.getMana() - k);
    }
}
