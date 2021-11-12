package controller;
import model.*;
import view.*;
import java.io.IOException;
import java.util.Vector;

public class Manager {
    private final StudentManager studentManager = new StudentManager();
    private final TeacherManager teacherManager = new TeacherManager();
    private final MissionManager missionManager = new MissionManager();
    private final FileManager fileManager = new FileManager();
    private final ConsoleView consoleView = new ConsoleView();
    private final MenuView menuView = new MenuView();

    private boolean inGame = false;
    private boolean inMenu = false;
    private final Menu menu = new Menu();
    private Student player;
    private Vector<Student> PlayersList;

    public Manager() {
        PlayersList = fileManager.init_old_Players();
        if(PlayersList == null){
            PlayersList = new Vector<>(5);
            menu.select_game.available = false;
            menu.score.available = false;
        }
    }
    public void start() throws IOException {
        inMenu = true;
        consoleView.helloFriend();
        while (inMenu) {
            inMainMenu();
            if(player != null && player.getMana() <= 0){
                consoleView.defeat();
            }
        }
    }

    private void inMainMenu() throws IOException {
        if(player != null){
            menuView.currentAcc(player);
        }
        menuView.printMainMenu();
        int item = menuView.getMenuItem();

        if(item == 1 && menu.new_game.available){
            player = studentManager.createStudent();
            inGame = true;
            PlayersList.add(player);

        }
        else if(item == 2){
            if(!menu.select_game.available){
                menuView.createAccFirstWarning();
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
            fileManager.saveGame(PlayersList);
            return;
        }
        if((item == 1 && menu.new_game.available)
                || (item == 2 && menu.select_game.available)){
            inGame = true;
            menu.select_game.available = true;
            menu.score.available = true;
            Teacher teacher = teacherManager.createTeacher();
            missionManager.generateMissions(player,teacher);
            consoleView.getPersonalInfo(player);
            missionManager.openMission(player,teacher);

            while (inGame){
                inGame = inGameMenu(player);
            }
        }
    }

    public boolean inGameMenu(Student player) throws IOException {
        menuView.printGameMenu();
        int item = menuView.getMenuItem();

        consoleView.getPersonalInfo(player);
        if(item == 1){
            Teacher teacher = teacherManager.createTeacher();
            missionManager.openMission(player,teacher);
            return true;
        }
        return false;

    }
}
