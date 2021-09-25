import java.util.Scanner;

public class Menu {

    public static void hello(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....");
    }

    public static void printMenu(){
        System.out.println("Start MENU:\n" +
                "1. New Game\n" +
                "2. Continue\n" +
                "3. My score\n" +
                "4. Quite game");
    }

    public static  int getMenuItem(){
        int n;
        Scanner in = new Scanner(System.in);

        do{
            System.out.print("Enter menu item: ");
            n = in.nextInt();
        }while(n > 4 || n < 1);

        return n;
    }

}
