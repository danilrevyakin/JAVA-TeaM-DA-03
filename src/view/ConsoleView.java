package view;
import model.*;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class ConsoleView implements Serializable {
    public boolean open(Mission mission, Teacher teacher){

        System.out.println("Welcome to the " + mission.getMissionNumber() + " mission 8).\n" +
                "Your teacher is " + teacher.getName() + " (" + teacher.getHealth() + "HP)");

        return true;
    }

    public void getPersonalInfo(Student person){
        System.out.println("\n" + person.getName() +  " (level: "+ person.getLevel() +
                "; health: " + person.getHealth() + "; mana: " +  person.getMana() + "): ");
    }

    public void choosingMission(Vector<Mission> missions){
        System.out.print("\nChoose mission(1 - " + missions.size() + "): ");
    }

    public int missinNumScanner(int missinNum){
        Scanner in = new Scanner(System.in);
        missinNum = in.nextInt();
        return missinNum;
    }

    public void quiz(Mission mission){
        System.out.println("Your task: " + mission.getQuestion());
        System.out.print("Choices: ");
        for (String choice : mission.getChoices()) {
            System.out.print(choice + "  ");
        }
        System.out.print("\n");
    }

    public void correctAnswerOutput(Teacher teacher){
        System.out.println("Correct! Now " + teacher.getName() + " has " + teacher.getHealth() + "HP");
    }

    public void tryAgain(int hp){
        System.out.println("Wrong, now your health is " + hp + "... Try again :(");
    }

    public void studentDefeat(){
        System.out.println("You were defeated by the boss! See you at the session...");
    }

    public void helloFriend(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }

    public void victory(){
        System.out.println("VICTORY!!!");
    }

    public void teacherDefeat(){
        System.out.println("OMG, You're correct...again... My colleagues will take revenge!");
    }
}
