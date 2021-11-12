package model;

import controller.*;

import java.util.ArrayList;

public class Mission {
    private int missionNumber;
    private final Teacher teacher;
    private final Student student;
    private String question;
    private String answer;
    private ArrayList<String> choices;

    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        this.student = student;
    }

    //getters & setters

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setChoices(ArrayList<String> choices){
        this.choices = choices;
    }

    public int getMissionNumber(){
        return missionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getAnswer() {
        return answer;
    }
}
