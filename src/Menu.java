import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedList;

public class Menu {
    public item new_game;
    public item continue_game;
    public item score;
    public item quit;
    public static LinkedList<item> Items;
    public static void hello(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }
    public Menu(){
        new_game = new item(1,"New Game");
        continue_game = new item(2, "Continue");
        score = new item(3, "My score");
        quit = new item(4, "Quite game");
        Items = new LinkedList<item>();
        Items.add(new_game);
        Items.add(continue_game);
        Items.add(score);
        Items.add(quit);
    }
    public class item{
        public boolean available = true;
        public String name;
        public int number;
        public item(int number, String name){
            this.name = number + ". " + name;
            this.number = number;
        }
    }
    public static int printMainMenu(){
        int range = 4;
        System.out.println("\nStart MENU:");
        for (item one:
             Items) {
            if(one.available){
                System.out.println(one.name);
            }
        }
        return range;
    }

    public static int printGameMenu(){
        int range = 3;

        System.out.println(
                "\t1. Continue\n" +
                "\t2. Back to menu");

        return range;
    }

    public static  int getMenuItem(int range){
        int n;
        Scanner in = new Scanner(System.in);

        do{
            System.out.print("Enter menu item: ");
            n = in.nextInt();
        }while(n > range || n < 1);

        return n;
    }

}
