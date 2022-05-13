package model;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import controller.MissionManager;
import view.ConsoleView;

public class Student extends Person implements Serializable {
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 10;
    private static final int MAX_HEALTH = 50;

    private final Player player;
    private final User user;
    private Mission currentMission;
    private int counterAvailableMissions = MissionManager.MAX_NUMBER_OF_MISSIONS;

    private final ConsoleView consoleView = new ConsoleView();
    public List<Mission> missions;

    public Student(Player player, User user) throws IOException {
        this.player = player;
        this.user = user;
        this.player.setUser(this.user);
        this.user.setPlayer(this.player);
    }

    public void correctStudentAnswer(){
        player.setMana(player.getMana() + 10);
        player.setScore(player.getScore() + 10);
        player.setHealth(MAX_HEALTH);

        if (player.getScore() >= 50){
            player.setLevel(player.getLevel() + 1);
            player.setScore(player.getScore() - 50);
        }

        if(player.getLevel() >= getMaxLevel()){
            consoleView.victory();
        }

        if (player.getMana() >= getMaxMana()){
            player.setMana(MAX_MANA);
        }
    }

    public void wrongStudentAnswer(){
        player.setHealth(player.getHealth() - 10);
        player.setMana(player.getMana() - 10);

        if(player.getHealth() <= 0){
            consoleView.studentDefeat();
        }
        if(player.getHealth() > 0){
            consoleView.tryAgain(getHealth());
        }
        if (player.getMana() < 0){
            player.setMana(0);
        }
    }

    public boolean hasAvailableMission() {
        return counterAvailableMissions > 0;
    }
    public void decreaseCounterAvailableMissions(){
    	--counterAvailableMissions;
    }

    public void setScore(int score) {
        player.setScore(score);
    }

    public int getScore() {
        return player.getScore();
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public int getMaxLevel(){
        return MAX_LEVEL;
    }

    public int getMana() {
        return player.getMana();
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void setMana(int mana){
        player.setMana(mana);
    }

    public int getHealth(){return player.getHealth();}

    public void setHealth(int health){player.setHealth(health);}

    public Mission getCurrentMission(){return currentMission;}

    public void setCurrentMission(Mission currentMission){this.currentMission = currentMission;}

    public String getLogin(){
        return user.getLogin();
    }

    public User getUser() {
        return user;
    }

    public Player getPlayer() {
        return player;
    }
}
