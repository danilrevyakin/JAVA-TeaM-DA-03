package model;

import view.ConsoleView;

import java.io.Serializable;
import java.util.List;

public abstract class Teacher extends Person implements Serializable{
    private int id;
    protected final ConsoleView consoleView = new ConsoleView();

    private List<Question> questions;

    private Student student;

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public Teacher(String name, String sex, List<Question> questions, int id){
        super(name, sex, 50);
        this.questions = questions;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> giveQuestion() {
        return questions;
    }

    public void correctStudentAnswer(){
        setHealth(getHealth() - 25);
        if(getHealth() <= 0){
            consoleView.teacherDefeat();
            return;
        }
        if (getHealth() > 0) consoleView.correctAnswerOutput(this);
        correctStudentAnswerSkill();
    }

    protected abstract void correctStudentAnswerSkill();

    protected abstract void wrongStudentAnswerSkill();

    public void wrongStudentAnswer(){
        setHealth(getHealth() + 5);
        wrongStudentAnswerSkill();
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", sex='" + getSex() + '\'' +
                ", questions=" + questions +
                '}';
    }

    public void setStudent(Student student){
        this.student = student;
    }
}