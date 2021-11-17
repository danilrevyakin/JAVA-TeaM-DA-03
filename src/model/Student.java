package model;
import view.ConsoleView;

import java.io.Serializable;
import java.util.Vector;

import controller.MissionManager;

public class Student extends Person implements Serializable {
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 10;
    private static final int MAX_HEALTH = 50;

    private int mana = 50;
    private int level = 1;
    private int counter_evailable_missions = MissionManager.MAX_NUMBER_OF_MISSIONS;
    public int score = 0;
    private final int DEFAULT_HP = 50;
    private final ConsoleView consoleView = new ConsoleView();
    private Vector<String> stateMissions;
    public Vector<Mission> missions;
    
    public Student(){
    	
        setMana(mana);
        setLevel(level);
        setHealth(DEFAULT_HP);
        setScore(score);
    }
    
    public Student(String name, boolean sex){
        setMana(mana);
        setLevel(level);
        setName(name);
        setSex(sex);
        setHealth(DEFAULT_HP);
        setScore(score);
    }

    public void correctStudentAnswer(){
        setMana(getMana() + 10);
        setScore(getScore() + 10);
        setHealth(MAX_HEALTH);

        if (getScore() >= 50){
            setLevel(getLevel() + 1);
            setScore(getScore() - 50);
        }

        if(getLevel() >= getMaxLevel()){
            consoleView.victory();
        }

        if (getMana() >= getMaxMana()){
            setMana(MAX_MANA);
        }
    }

    public void wrongStudentAnswer(){
        setHealth(getHealth() - 10);
        setMana(getMana() - 10);

        if(getHealth() <= 0){
            consoleView.studentDefeat();
        }

        if (getMana() < 0){
            setMana(0);
        }

        if(getHealth() > 0){
            consoleView.tryAgain(getHealth());
        }
    }

    public boolean has_available_mission() {
        if(counter_evailable_missions > 0) return true;
        return false;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public int getMaxLevel(){
        return MAX_LEVEL;
    }

    public int getMana() {
        return mana;
    }

    public int getLevel() {
        return level;
    }

    public int setLevel(int level){
        return this.level = level;
    }

    public int setMana(int mana){
        return this.mana = mana;
    }

    public int getCounter_evailable_missions() {
        return counter_evailable_missions;
    }

    public void setCounter_evailable_missions(int counter_evailable_missions) {
        this.counter_evailable_missions = counter_evailable_missions;
    }


}
