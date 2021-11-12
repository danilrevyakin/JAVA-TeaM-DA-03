package controller;
import model.*;
import view.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Manager {
    private final StudentManager studentManager = new StudentManager();
    private final TeacherManager teacherManager = new TeacherManager();
    private final MissionManager missionManager = new MissionManager();
    private final FileManager fileManager = new FileManager();
    private final ConsoleView consoleView = new ConsoleView();

    private boolean inGame = false;
    private boolean inMenu = false;
    private final Menu menu = new Menu();
    private Student player;
    private Vector<Student> PlayersList;
    private final Scanner in = new Scanner(System.in);

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
        consoleView.hello();
        while (inMenu) {
            inMainMenu();
            if(player != null && player.getMana() <= 0){
                consoleView.defeat();
            }

        }
    }

    private void inMainMenu() throws IOException {
        if(player != null){
            System.out.println("Current account: " + player.getName());
        }
        int range =  consoleView.printMainMenu();
        int item = menu.getMenuItem(range);
        if(item == 1 && menu.new_game.available){
            player = studentManager.createStudent();
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
                inGame = menu.inGameMenu(player);
            }
        }
    }
}
