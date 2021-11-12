package view;
import controller.Menu;
import model.*;
import java.util.Scanner;
import java.util.Vector;

import static controller.Menu.Items;

public class ConsoleView {
    public boolean open(Mission mission, Teacher teacher){

        System.out.println("Welcome to the " + mission.getMissionNumber() + " mission 8).\n" +
                "Your teacher is " + teacher.getName());

        return true;
    }

    public void getPersonalInfo(Student person){
        System.out.println("\n" + person.getName() +  " (level: "+ person.getLevel() +
                "; mana: " + person.getMana() + "): ");
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

    public void correctAnswer(){
        System.out.println("Correct!");
    }

    public void tryAgain(){
        System.out.println("Try again :(");
    }

    public void defeat(){
        System.out.println("You were defeated by the boss! See you at the session...");
    }

    public void hello(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }

    public int printMainMenu(){
        int range = 4;
        System.out.println("\nStart MENU:");
        for (Menu.item one:
                Items) {
            System.out.println(one.name);
        }
        return range;
    }

    public int printGameMenu(){
        int range = 3;

        System.out.println(
                "\t1. Continue\n" +
                        "\t2. Back to menu");

        return range;
    }
}
