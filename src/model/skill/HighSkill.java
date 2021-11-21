package model.skill;

import model.Question;
import model.Student;

import java.io.Serializable;


public class HighSkill implements Skill, Serializable {

    @Override
    public void studentAnswerCorrect(Student student, Question question) { student.score -= 2; }

    @Override
    public void studentAnswerFalse(Student student, Question question) { student.score -= (int) (Math.random() * 5); }
}
