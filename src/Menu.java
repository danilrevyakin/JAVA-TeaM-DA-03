import java.util.Scanner;

public class Menu {

    public static void hello(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }

    public static int printStartMenu(){
        int range = 4;
        System.out.println("\nStart MENU:\n" +
                "1. New Game\n" +
                "2. Continue\n" +
                "3. My score\n" +
                "4. Quite game");
        return range;
    }

    public static int printGameMenu(){
        int range = 3;

        System.out.println(
                "\t1. Continue\n" +
                "\t2. Save game;\n" +
                "\t3. Quite game");

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
