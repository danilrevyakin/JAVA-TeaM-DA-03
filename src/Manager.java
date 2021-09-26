import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Manager {
    private static boolean inGame = false;
    private static Menu menu;
    private static Student player;
    private static Map gameMap = new Map();
    private static Vector<Student> PlayersList;
    private int item;
    private final Scanner in = new Scanner(System.in);

    public Manager() throws FileNotFoundException {
        PlayersList = FileManager.init_old_Players();
        menu = new Menu();
        if(PlayersList == null){
            PlayersList = new Vector<>(5);
            menu.continue_game.available = false;
            menu.score.available = false;
        }
    }
    public void start() throws FileNotFoundException {
        inGame = true;
        Menu.hello();
        while (inGame) {
            inMainMenu();
            if(player.getMana() <= 0){
                System.out.println("YOU DIED");
            }
        }
    }
    private boolean inGameMenu(){
        int range = Menu.printGameMenu();
        int item = Menu.getMenuItem(range);
        player.info();
        if(item == 1) gameMap.openMission();
        if (item == 2) return false;
        return true;
    }
    private void inMainMenu(){
        int range =  Menu.printMainMenu();
        int item = Menu.getMenuItem(range);
        if(item == 1){
            player = Game.createStudent();
            inGame = true;
            PlayersList.add(player);
        }
        else if(item == 2){
            System.out.println("\nPlease, select your account");
            for (int i = 0; i < PlayersList.size(); ++i) {
                Student one = PlayersList.elementAt(i);
                System.out.println("#" + (i + 1) + " Name: " + one.getName() + "score: " + one.getLevel());
            }
            System.out.println("Back to menu: 0");
            int choice = -1;
            while(choice < 0 || choice > PlayersList.size()){
                System.out.println("Enter: ");
                choice = in.nextInt();
            }

            if(choice == 0){
                item = 0;
            }else {
                player = PlayersList.elementAt(choice - 1);
            }

        }
        else if(item == 3){
            System.out.println("Your score is " + player.score);
        }
        else if(item == 4){
            inGame = false;
            FileManager.saveGame(PlayersList);
            return;
        }
        if((item == 1) || (item == 2)){
            menu.continue_game.available = true;
            menu.score.available = true;
            gameMap.generateMissions(player);
            gameMap.openMission();
            inGameMenu();
        }
    }
}
