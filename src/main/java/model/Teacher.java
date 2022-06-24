package model;

import model.modes.Easy;
import model.modes.Mode;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public abstract class Teacher extends Person implements Serializable {
    private int id;
    protected final ConsoleView consoleView = new ConsoleView();

    private final List<Question> questions;

    private Student student;
    protected Mode mode = new Easy();

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    Iterator<Question> iterator;

    public Teacher(String name, String sex, List<Question> questions, int id) {
        super(name, sex, 50);
        this.questions = questions;
        iterator = questions.listIterator();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question giveNextQuestion() {
        if (!iterator.hasNext()){
            return null;
        }
        Question question = iterator.next();
        return question;
    }

    public void correctStudentAnswer() {
        setHealth(getHealth() - 25);
        if (getHealth() <= 0) {
            consoleView.teacherDefeat();
            return;
        }
        if (getHealth() > 0) consoleView.correctAnswerOutput(this);
        correctStudentAnswerSkill();
    }

    protected abstract void correctStudentAnswerSkill();

    protected abstract void wrongStudentAnswerSkill();

    public void wrongStudentAnswer() {
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

    public boolean hasQuestions(){
        return iterator.hasNext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mode getMode() {
        return mode;
    }

    public String getModeName(){
        StringBuilder stringBuilder = new StringBuilder(mode.getClass().getName());
        return stringBuilder.substring(stringBuilder.lastIndexOf(".") + 1);
    }

}