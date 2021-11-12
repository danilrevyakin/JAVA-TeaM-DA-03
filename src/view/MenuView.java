package view;
import model.Menu;
import model.Student;

import java.util.Scanner;
import java.util.Vector;

import static model.Menu.Items;

public class MenuView {

    public int getMenuItem(){
        final int MAX_RANGE = 4;
        int n;
        Scanner in = new Scanner(System.in);
        do{
            System.out.print("Enter menu item: ");
            n = in.nextInt();
        }while(n > MAX_RANGE || n < 1);

        return n;
    }
    public void printMainMenu(){
        System.out.println("\nStart MENU:");
        for (Menu.item one:
                Items) {
            System.out.println(one.name);
        }
    }

    public void printGameMenu(){
        System.out.println(
                "\t1. Continue\n" +
                        "\t2. Back to menu");
    }

    public void selectAccount(Vector<Student> PlayersList){
        System.out.println("\nPlease, select your account");
        for (int i = 0; i < PlayersList.size(); ++i) {
            Student one = PlayersList.elementAt(i);
            System.out.println("#" + (i + 1) + " Name: " + one.getName() + " level: " + one.getLevel());
        }

        System.out.println("Back to menu: 0");
    }

    public int enterAccount(Vector<Student> PlayersList){
        final Scanner in = new Scanner(System.in);
        int choice = -1;

        while(choice < 0 || choice > PlayersList.size()){
            System.out.println("Enter: ");
            choice = in.nextInt();
        }

        return choice;
    }

    public void currentAcc(Student player){
        System.out.println("Current account: " + player.getName());
    }

    public void createAccFirstWarning(){
        System.out.println("\nPlease create your own account first");
    }

    public void myScoreInfo(Student player, Menu menu){
        if(player == null || !menu.score.available){
            System.out.println("\nPlease, select your account or create new");
        }else {
            System.out.println("Your score is " + player.score);
        }
    }
}
