package controller;
import model.*;
import view.*;
import java.util.Vector;

public class Manager {
    private final StudentManager studentManager = new StudentManager();
    private final MissionManager missionManager = new MissionManager();
    private final ConsoleView consoleView = new ConsoleView();
    private final MenuView menuView = new MenuView();
    private final int CONTINUE_MISSION = 1; 
    private boolean inGame = false;
    private boolean inMenu = false;
    private final Menu menu = new Menu();
    private Student player;
    private Vector<Student> PlayersList;

    public Manager() {
        PlayersList = FileManager.init_old_Players();
        if(PlayersList == null){
            PlayersList = new Vector<>(5);
            menu.select_game.available = false;
            menu.score.available = false;
        }
    }
    public void start() {
        inMenu = true;
        consoleView.helloFriend();
        while (inMenu) {
            inMainMenu();
            if(player != null && player.getHealth() <= 0){
                inMenu = false;
            }
        }
    }

    private void inMainMenu() { // 1 new Player, 2 Select created Player, 3 My score, 4 Exit the program
        if(player != null) menuView.currentAcc(player);
        menuView.printMainMenu();
        int item = menuView.getMenuItem();
        
        if(item == 1 && menu.new_game.available){
            player = studentManager.createStudent();
            missionManager.generateMissions(player);
            inGame = true;
            PlayersList.add(player);

        }
        else if(item == 2){
            if(!menu.select_game.available){
                menuView.createAccFirstWarning();
                inGame = false;
            }
            menuView.selectAccount(PlayersList);
            int choice = menuView.enterAccount(PlayersList);

            if(choice == 0){
                item = 0;
            }else {
                player = PlayersList.elementAt(choice - 1);
            }

        }
        else if(item == 3){
           menuView.myScoreInfo(player,menu);
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
            consoleView.getPersonalInfo(player);
            missionManager.openMission(player);      
            while (inGame){
                inGame = inGameMenu();
            }
        }
    }

    private boolean inGameMenu() { // 1 Continue, 2 Exit to mainMenu
        
    	if (player.getHealth() <= 0){
            PlayersList.remove(player); // This is IASA! (SPARTA)
            return false;
        }
        menuView.printGameMenu();
        int item = menuView.getMenuItem();

        consoleView.getPersonalInfo(player);
        if(item == CONTINUE_MISSION){     	
        	missionManager.openMission(player);
            return true;
        }
        return false;
    }
}
