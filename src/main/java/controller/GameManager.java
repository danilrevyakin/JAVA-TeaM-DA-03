package controller;

import hibernateUtil.PlayerDao;
import javafx.fxml.FXML;
import model.Student;
import view.ConsoleView;
import view.MenuView;

import java.awt.*;

public class GameManager {
    @FXML
    private Button backToMenuButton;

    @FXML
    private Button selectMissionButton;

    private final MissionManager missionManager = new MissionManager();
    private final ConsoleView consoleView = new ConsoleView();
    private final MenuView menuView = new MenuView();
    private boolean inGame = false;
    private boolean inMenu = false;
    
    public void newPlayer(Student student) {
        missionManager.generateMissions(student);
        inGame = true;
        playerIsSelected(student);
    }
    
    public void selectCreatedPlayer(Student student) {
        getAvailableMissions(student);
        playerIsSelected(student);
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
    
    private void playerIsSelected(Student student) {
    	inGame = true;
        consoleView.getPersonalInfo(student);
        missionManager.openMission(student);
        while (inGame){
            inGame = inGameMenu(student);
        }
    }
    
    private boolean inGameMenu(Student student) { // 1 SELECT MISSION, 2 Exit to mainMenu
        final int SELECT_MISSION = 1;
        final int BACK_TO_MENU = 2;
        final boolean CONTINUE = true;
        final boolean EXIT = false;
        
    	if (student.getPlayer().getHealth() <= 0){
            return EXIT;
        }
        menuView.printGameMenu();
        int choice = menuView.getMenuItem();

        consoleView.getPersonalInfo(student);
        if(choice == SELECT_MISSION){
        	missionManager.openMission(student);
            return CONTINUE;
        }

        if (choice == BACK_TO_MENU){
            exitTheProgram(student);
        }
        return EXIT;
    }
    
    private void exitTheProgram(Student student) {
    	inMenu = false;
        PlayerDao playerDao = new PlayerDao();
        playerDao.update(student.getPlayer());
    }
}
