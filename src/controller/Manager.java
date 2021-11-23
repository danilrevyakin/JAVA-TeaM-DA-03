package controller;
import java.util.Vector;

import model.Student;
import view.ConsoleView;
import view.MenuView;

public class Manager {
    private final StudentManager studentManager = new StudentManager();
    private final MissionManager missionManager = new MissionManager();
    private final ConsoleView consoleView = new ConsoleView();
    private final MenuView menuView = new MenuView();
    private boolean inGame = false;
    private boolean inMenu = false;
    private Student player;
    private Vector<Student> PlayersList;
    
    private final int ITEM_NEW_PLAYER = 1;
    private final int ITEM_SELECT_CREATED_PLAYER = 2;
    private final int ITEM_EXIT_THE_PROGRAM = 3;
    
    private int item = -100;
    
    public Manager() {
        PlayersList = FileManager.initOldPlayers();
        if(PlayersList == null){
            PlayersList = new Vector<>(5);
        }
    }
    
    public void start() {
        inMenu = true;
        consoleView.helloFriend(); 
        while (inMenu) {
            inMainMenu();
        }
    }
    
    private void inMainMenu() { // 1 new Player, 2 Select created Player, 3 Exit the program
        menuView.printMainMenu();
        item = menuView.getMenuItem();
        if(item == ITEM_NEW_PLAYER){
        	newPlayer();
        }
        else if(item == ITEM_SELECT_CREATED_PLAYER){
        	selectCreatedPlayer();
        }
        else if(item == ITEM_EXIT_THE_PROGRAM ){
        	exitTheProgram();
        }
    }
    
    private void newPlayer() {
    	player = studentManager.createStudent();
        missionManager.generateMissions(player);
        inGame = true;
        PlayersList.add(player);
        playerIsSelected();
    }
    
    private void selectCreatedPlayer() {
    	final int BACK_TO_MAIN_MENU = 0;
    	if(!checkExistingPlayers()){
            menuView.createAccFirstWarning();
            inGame = false;
            return;
        }
        else{
        	this.studentManager.sortStudents(PlayersList);
        	menuView.selectAccount(PlayersList);
            int choice = menuView.enterAccount(PlayersList);
            if (choice != BACK_TO_MAIN_MENU) {
            	player = PlayersList.elementAt(choice - 1);
                playerIsSelected();
            }
        }
    }
    
    private void playerIsSelected() {
    	inGame = true;
        consoleView.getPersonalInfo(player); 
        missionManager.openMission(player);
        while (inGame){
            inGame = inGameMenu();
        }
    }
    
    private boolean inGameMenu() { // 1 SELECT MISSION, 2 Exit to mainMenu
        final int SELECT_MISSION = 1; 
        final boolean CONTINUE = true;
        final boolean EXIT = false;
        
    	if (player.getHealth() <= 0){
            PlayersList.remove(player); // This is IASA! (SPARTA)
            player = null;
            return EXIT;
        }
        menuView.printGameMenu();
        int choice = menuView.getMenuItem();

        consoleView.getPersonalInfo(player);
        if(choice == SELECT_MISSION){
        	missionManager.openMission(player);
            return CONTINUE;
        }
        return EXIT;
    }
    
    private void exitTheProgram() {
    	inMenu = false;
    	this.studentManager.sortStudents(PlayersList);
        FileManager.saveGame(PlayersList);
    }
    
    private boolean checkExistingPlayers() {
    	if(PlayersList == null) return false;
    	if(PlayersList.size() == 0) return false;
    	return true;
    }
}
