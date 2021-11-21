package model.skill;

import model.Question;
import model.Student;

import java.io.Serializable;

public class RomanovSkill implements Skill, Serializable {
    @Override
    public void studentAnswerCorrect(Student student, Question question) { student.score += (int) (Math.random() * 5); }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        student.setMana(student.getMana() - 10 * (int) (Math.random() * 2)); //Lowering your mana by a couple of dozen
    }
}
