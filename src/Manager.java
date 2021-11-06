import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Manager {
    private static boolean inGame = false;
    private static boolean inMenu = false;
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
            menu.select_game.available = false;
            menu.score.available = false;
        }
    }
    public void start() throws IOException {
        inMenu = true;
        Menu.hello();
        while (inMenu) {
            inMainMenu();
            if(player != null && player.getMana() <= 0){
                System.out.println("YOU DIED");
            }

        }
    }
    //Add to Menu class
    private boolean inGameMenu(){
        int range = Menu.printGameMenu();
        int item = Menu.getMenuItem(range);
        player.info();
        if(item == 1){
            gameMap.openMission();
            return true;
        }
        return false;

    }
    private void inMainMenu() throws IOException {
        if(player != null){
            System.out.println("Current account: " + player.getName());
        }
        int range =  Menu.printMainMenu();
        int item = Menu.getMenuItem(range);
        if(item == 1 && menu.new_game.available){
            player = Game.createStudent();
            inGame = true;
            PlayersList.add(player);

        }
        else if(item == 2){
            if(!menu.select_game.available){
                System.out.println("\nPlease create your own account first");
                return;
            }
            System.out.println("\nPlease, select your account");
            for (int i = 0; i < PlayersList.size(); ++i) {
                Student one = PlayersList.elementAt(i);
                System.out.println("#" + (i + 1) + " Name: " + one.getName() + " level: " + one.getLevel());
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
            if(player == null || !menu.score.available){
                System.out.println("\nPlease, select your account or create new");
            }else {
                System.out.println("Your score is " + player.score);
            }
        }
        else if(item == 4 && menu.quit.available){
            inMenu = false;
            FileManager.saveGame(PlayersList);
            return;
        }
        if((item == 1 && menu.new_game.available)
                || (item == 2 && menu.select_game.available)){
            inGame = true;
            menu.select_game.available = true;
            menu.score.available = true;
            gameMap.generateMissions(player);
            player.info();
            gameMap.openMission();

            while (inGame){
                inGame = inGameMenu();
            }
        }
    }
}
