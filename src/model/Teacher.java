package model;

import java.io.Serializable;
import java.util.ArrayList;

import view.ConsoleView;

public abstract class Teacher extends Person implements Serializable{
    protected static final int DEFAULT_HP = 50; // Protected because I wanna class child be able to see this field 
    protected final ConsoleView consoleView = new ConsoleView();
    private ArrayList<Question> questions;
    protected Student student;
    protected Question lastQuestion;
    public Teacher(String name, boolean sex, ArrayList<Question> questions){
    	super(name, sex, DEFAULT_HP);
        this.questions = questions;
    }
    
    public ArrayList<Question> give_Question() {
        return questions;
    }

//    public int getNumberOfQuestion() {
//    	return questions.size();
//    }
    public void setStudent(Student student) {
    	this.student = student;
    }
    public void setLastQuestion(Question question) {
    	this.lastQuestion = question;
    }
    public void correctStudentAnswer(){
        setHealth(getHealth() - 25);
        if(getHealth() <= 0){
            consoleView.teacherDefeat();
            return;
        }
        correctStudentAnswerSkill();
    }
    protected abstract void correctStudentAnswerSkill();
    
    protected abstract void wrongStudentAnswerSkill();
    
    public void wrongStudentAnswer(){
        setHealth(getHealth() + 5);
        wrongStudentAnswerSkill();
    }
}
