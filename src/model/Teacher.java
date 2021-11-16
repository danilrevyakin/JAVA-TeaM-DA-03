package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import view.ConsoleView;

public class Teacher extends Person implements Serializable{
    private final int DEFAULT_HP = 50;
    private final ConsoleView consoleView = new ConsoleView();
    private ArrayList<Question> questions;
    private static final Random rand = new Random();
    private int Num_of_question;
    
    public Teacher(String name, ArrayList<Question> questions){
        setName(name);
        setHealth(DEFAULT_HP);
        this.questions = questions;
    }
    
    public Question give_Question() {
    	int size = questions.size();
    	if(size <= 0) 
    		return null;
    	int num = rand.nextInt() % size;
    	num = Math.abs(num);
    	return questions.remove(num);
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

    public void wrongStudentAnswer(){
        setHealth(getHealth() + 5);
    }
}
