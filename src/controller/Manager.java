package controller;
import model.*;
import view.*;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

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
        PlayersList = FileManager.init_old_Players();
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
    
    private void select_createdPlayer() {
    	final int BACK_TO_MAIN_MENU = 0;
    	if(!check_existingPlayers()){
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
                Player_is_selected();
            }
        }
    }
   
    private boolean check_existingPlayers() {
    	if(PlayersList == null) return false;
    	if(PlayersList.size() == 0) return false;
    	return true;
    }
    
    private void newPlayer() {
    	player = studentManager.createStudent();
        missionManager.generateMissions(player);
        inGame = true;
        PlayersList.add(player);
        Player_is_selected();
    }
    
    private void inMainMenu() { // 1 new Player, 2 Select created Player, 3 My score, 4 Exit the program
        menuView.printMainMenu();
        item = menuView.getMenuItem();
        if(item == ITEM_NEW_PLAYER){
        	newPlayer();
        }
        else if(item == ITEM_SELECT_CREATED_PLAYER){
        	select_createdPlayer();
        }
        else if(item == ITEM_EXIT_THE_PROGRAM ){
        	exit_theProgram();
        }
    }
    
    private void Player_is_selected() {
    	inGame = true;
        consoleView.getPersonalInfo(player); 
        missionManager.openMission(player);
        while (inGame){
            inGame = inGameMenu();
        }
    }
    
    private void exit_theProgram() {
    	inMenu = false;
    	this.studentManager.sortStudents(PlayersList);
        FileManager.saveGame(PlayersList);
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
}
