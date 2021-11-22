package model;
import java.io.Serializable;
import java.util.Vector;

import controller.MissionManager;
import view.ConsoleView;

public class Student extends Person implements Serializable {
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 10;
    private static final int MAX_HEALTH = 50;

    private int mana = 50;
    private int level = 1;
    private Mission currentMission;
    private int counterAvailableMissions = MissionManager.MAX_NUMBER_OF_MISSIONS;
    private int score = 0;
    private final ConsoleView consoleView = new ConsoleView();
    public Vector<Mission> missions;

    
    
    public Student(String name, boolean sex){
    	super(name, sex, MAX_HEALTH);
        setMana(mana);
        setLevel(level);
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

    public boolean hasAvailableMission() {
        if(counterAvailableMissions > 0) return true;
        return false;
    }
    public void decreaseCounterAvailableMissions(){
    	--counterAvailableMissions;
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

    public int getCounterAvailableMissions() {
        return counterAvailableMissions;
    }

    public void setCounterAvailableMissions(int counterAvailableMissions) {
        this.counterAvailableMissions = counterAvailableMissions;
    }
    
    public Mission getCurrentMission(){return currentMission;}

    public void setCurrentMission(Mission currentMission){this.currentMission = currentMission;}
}
