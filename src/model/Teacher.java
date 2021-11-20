package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import controller.FileManager;
import view.ConsoleView;

public abstract class Teacher extends Person implements Serializable{
    protected static final int DEFAULT_HP = 50; // Protected because I wanna class child be able to see this field 
    private final ConsoleView consoleView = new ConsoleView();
    private ArrayList<Question> questions;
    private static final Random rand = new Random();
    protected Student student;
    protected Question lastQuestion;
    
    public Teacher(String name, boolean sex, ArrayList<Question> questions){
    	super(name, sex, DEFAULT_HP);
        this.questions = questions;
    }
    
    public ArrayList<Question> give_Question() {
        return questions;
    }

    public int getNumber_of_Question() {
    	return questions.size();
    }

    public void correctStudentAnswer(){
        setHealth(getHealth() - 25);

        if(getHealth() <= 0){
            consoleView.teacherDefeat();
        }
    }
    protected abstract void correctStudentAnswerSkill();
    
    protected abstract void wrongStudentAnswerSkill();
    
    public void wrongStudentAnswer(){
        setHealth(getHealth() + 5);
    }
}
