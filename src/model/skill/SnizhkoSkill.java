package model.skill;

import model.Question;
import model.Student;

import java.io.Serializable;

public class SnizhkoSkill implements Skill, Serializable {
    @Override
    public void studentAnswerCorrect(Student student, Question question) { student.score += (int) (Math.random() * 10); }

    @Override
    public void studentAnswerFalse(Student student, Question question) {
        student.setHealth(student.getHealth() + (int) (Math.random() * 5));
        student.setMana(student.getMana() + (int) (Math.random() * 10));
    }
}
