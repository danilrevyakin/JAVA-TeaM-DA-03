package model;

import java.util.ArrayList;
import java.util.Collections;

public class Questions {
    private final String question;
    private final String answer;
    private final ArrayList<String> choices;

    public Questions(String question, String answer, String[] choices){
        this.question = question;
        this.answer = answer;
        this.choices = new ArrayList<>();
        Collections.addAll(this.choices, choices);
        Collections.shuffle(this.choices);
    }

    //getters

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
