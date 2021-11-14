package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable{
    private final String question;
    private final String answer;
    private final ArrayList<String> choices;
    private boolean asked = false;
    public Question(String question, String answer, String[] choices){
        this.question = question;
        this.answer = answer;
        this.choices = new ArrayList<>();
        Collections.addAll(this.choices, choices);
        Collections.shuffle(this.choices);
    }
    
    public void setAsked() {
    	this.asked = true;
    }
    public void setUnAsked() {
    	this.asked = false;
    }
    //getters
    
    public boolean getAsked() {
    	return this.asked;
    }
    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

    public ArrayList<String> getChoices(){
        return choices;
    }
}
