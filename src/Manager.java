import java.util.Scanner;

public class Manager {
    private static boolean inGame = false;

    private static Student player;
    private static Map gameMap = new Map();

    public static boolean game() {
        if (!inGame) {
            Menu.hello();
            Menu.printMenu();
            int item = Menu.getMenuItem();

            if(item == 1){
                player = Game.createStudent();
                gameMap.generateMissions(player);
                inGame = true;
            }
            else if(item == 4) return false;
            else System.out.println("Sorry, I haven't finished writing the code yet");
        }

        while (inGame) {
            //System.out.println("In Game");
            gameMap.openMission();
        }

        return true;
    }
}
