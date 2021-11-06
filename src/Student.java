import java.io.Serializable;
import java.util.Scanner;

public class Student extends Person implements Serializable {
    private final int MAX_MANA = 100;
    private final int MANA = 50;
    private final int LEVEL = 1;
    private Mission currentMission;
    public int score = 0;

    public Student(){
        setMaxMana(MAX_MANA);
        setMana(MANA);
        setLevel(LEVEL);
    }

    public Student(String name, boolean sex){
        setMaxMana(MAX_MANA);
        setMana(MANA);
        setLevel(LEVEL);
        setName(name);
        setSex(sex);
        setMaxLevel(2);
    }

    //getters & setters

    public Mission getCurrentMission(){
        return currentMission;
    }

    public  void setCurrentMission(Mission currentMission){
        this.currentMission = currentMission;
    }

    //methods
    public boolean enterMission(Mission mission){
        return mission.open();
    }

    public String giveAnswer(){
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();

        return answer;
    }
}
