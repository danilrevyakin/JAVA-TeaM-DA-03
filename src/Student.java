import java.io.Serializable;
import java.util.Scanner;

public class Student extends Person implements Serializable {

    private Mission currentMission;
    public int score = 0;
    public Student(){
        setMaxMana(100);
        setMana(50);
        setLevel(1);
    }

    public Student(String name, boolean sex){
        setMaxMana(100);
        setMana(50);
        setLevel(1);
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
