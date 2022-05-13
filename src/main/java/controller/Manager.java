package controller;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;

import hibernateUtil.PlayerDao;
import hibernateUtil.UserDao;
import model.Mission;
import model.Player;
import model.Student;
import model.User;
import view.ConsoleView;
import view.MenuView;

public class Manager {
    private final StudentManager studentManager = new StudentManager();
    private final MissionManager missionManager = new MissionManager();
    private final ConsoleView consoleView = new ConsoleView();
    private final MenuView menuView = new MenuView();
    private final PlayerDao playerDao = new PlayerDao();
    private final UserDao userDao = new UserDao();
    private boolean inGame = false;
    private boolean inMenu = false;
    private Student player;
    private List<Student> PlayersList;
    
    private final int ITEM_NEW_PLAYER = 1;
    private final int ITEM_SELECT_CREATED_PLAYER = 2;
    private final int ITEM_EXIT_THE_PROGRAM = 3;
    
    private int item = -100;
    
    public Manager() {
        List<Player> players = playerDao.findAll();
        List<User> users = userDao.findAll();
        if (!players.isEmpty()) {
            PlayersList = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                try {
                    PlayersList.add(new Student(players.get(i), users.get(i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            PlayersList = new ArrayList<>(5);
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
        try {
            player = studentManager.createStudent();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            	player = PlayersList.get(choice - 1);
                getAvailableMissions(player);
                playerIsSelected();
            }
        }
    }

    private void getAvailableMissions(Student student){
        MissionManager manager = new MissionManager();
        manager.generateMissions(student);
        if (student.getPlayer().getCompletedMissions() != null) {

            String[] completed = student.getPlayer().getCompletedMissions().split(",");
            for (String s : completed) {
                int value = Integer.parseInt(s);
                student.missions.removeIf(mission -> mission.getTeacher().getId() == value);
                student.decreaseCounterAvailableMissions();
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
        PlayerDao playerDao = new PlayerDao();
        for (Student student : PlayersList) {
            try {
                playerDao.update(student.getPlayer());
            }
            catch (Exception e){
                System.out.println("Aboba");
            }
        }
    }
    
    private boolean checkExistingPlayers() {
        return PlayersList != null && !PlayersList.isEmpty();
    }
}
