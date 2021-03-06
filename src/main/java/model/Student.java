package model;

import controller.factory.MissionFactory;
import view.ConsoleView;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Student extends Person{
    public static final int MAX_MANA = 500;
    public static final int MAX_LEVEL = 10;
    public static final int MAX_HEALTH = 50;

    private final Player player;
    private final User user;
    private Mission currentMission;
    private int counterAvailableMissions = MissionFactory.MAX_NUMBER_OF_MISSIONS;

    private final ConsoleView consoleView = new ConsoleView();
    public Map<Integer, Mission> availableMissions;

    public Student(Player player, User user) throws IOException {
        this.player = player;
        this.user = user;
        this.player.setUser(this.user);
        this.user.setPlayer(this.player);
    }

    public void correctStudentAnswer(){
        increaseManaOn(30);
        player.setScore(player.getScore() + 10);
        player.setHealth(MAX_HEALTH);

        if (player.getScore() >= 50){
            player.setLevel(player.getLevel() + 1);
            player.setScore(player.getScore() - 50);
        }

        if(player.getLevel() >= Student.MAX_LEVEL){
            consoleView.victory();
        }

        if (player.getMana() >= Student.MAX_MANA){
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

    @Override
    public String getName(){
        return user.getName();
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

    public void increaseScoreOn(int points){
        this.setScore(getScore() + points);
    }

    public void increaseHealthOn(int points){
        this.setHealth(getHealth() + points);
    }

    public void increaseManaOn(int points){
        this.setMana(getMana() + points);
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

    public void decreaseLevelOn(int levels){
        this.setLevel(getLevel() - levels);
        if(getLevel() <= 0){
            setLevel(1);
        }
    }

    public void setLevel( int level){
        this.player.setLevel(level);
    }
    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return user.getLogin()
                + "\tLevel: " + player.getLevel()
                + "\tScore: " + player.getScore()
                + "\tMana: " + player.getMana()
                + "\tHealth: " + player.getHealth()
                + "\n";
    }
}
