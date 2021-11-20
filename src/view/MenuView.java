package view;
import static model.Menu.Items;

import java.util.Scanner;
import java.util.Vector;

import model.Menu;
import model.Student;

public class MenuView {
	Menu menu = new Menu();
	ConsoleView view = new ConsoleView();
	
    public int getMenuItem(){
        final int MAX_RANGE = 3;
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
                "\n\t1. Select Mission\n" +
                        "\t2. Back to menu");
    }

    public void selectAccount(Vector<Student> PlayersList){
        System.out.println("\nPlease, select your account");
        for (int i = 0; i < PlayersList.size(); ++i) {
            Student one = PlayersList.elementAt(i);
            System.out.print("#" + (i + 1) + "\t");
            view.getPersonalInfo(one);
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
        System.out.println("\nCurrent account: " + player.getName());
    }

    public void createAccFirstWarning(){
        System.out.println("\nPlease create your own account first");
    }

    public void myScoreInfo(Student player){
        if(player == null){
            System.out.println("\nPlease, select your account or create new");
        }else {
            System.out.println("Your score is " + player.score);
        }
    }
}
