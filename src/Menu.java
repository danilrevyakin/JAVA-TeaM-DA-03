import java.util.Scanner;

public class Menu {

    public void printMenu(){
        System.out.println("Start MENU:\n" +
                "1. New Game\n" +
                "2. Continue\n" +
                "3. My score\n" +
                "4. Quite game");
    }

    public int getMenuItem(int item){
        int n;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("Enter menu item: ");
            n = in.nextInt();
        }while(n > 4 || n < 1);

        return n;
    }

}
