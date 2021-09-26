import java.util.Scanner;

public class Manager {
    private static boolean inGame = false;

    private static Student player;
    private static Map gameMap = new Map();

    public static boolean game() {
        if (!inGame) {
            Menu.hello();
            int range =  Menu.printStartMenu();
            int item = Menu.getMenuItem(range);

            if(item == 1){
                player = Game.createStudent();
                gameMap.generateMissions(player);
                inGame = true;
                gameMap.openMission();
            }
            else if(item == 4) return false;
            else System.out.println("Sorry, I haven't finished writing the code yet");
        }

        while (inGame) {
            //System.out.println("In Game");
            if(player.getMana() <= 0){
                System.out.println("YOU DIED");
                return false;
            }

            player.info();
            int range = Menu.printGameMenu();
            int item = Menu.getMenuItem(range);

            if(item == 1) gameMap.openMission();
            if(item == 2) System.out.println("Save game");
            if (item == 3) return false;
        }

        return true;
    }
}
