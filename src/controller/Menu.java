package controller;
import model.*;
import view.ConsoleView;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class Menu { 
    public item new_game;
    public item select_game;
    public item score;
    public item quit;
    public static LinkedList<item> Items;

    private final ConsoleView consoleView = new ConsoleView();
    private final MissionManager missionManager = new MissionManager();
    private final TeacherManager teacherManager = new TeacherManager();

    public Menu(){
        new_game = new item(1, "New controller.Game");
        select_game = new item(2, "Select saved game");
        score = new item(3, "My score");
        quit = new item(4, "Quite game");
        Items = new LinkedList<>();
        Items.add(new_game);
        Items.add(select_game);
        Items.add(score);
        Items.add(quit);
    }
    public static class item{
        public boolean available = true;
        public String name;
        public int number;
        public item(int number, String name){
            this.name = number + ". " + name;
            this.number = number;
        }
    }

    public boolean inGameMenu(Student player) throws IOException {
        int range = consoleView.printGameMenu();
        int item = getMenuItem(range);

        consoleView.getPersonalInfo(player);
        if(item == 1){
            Teacher teacher = teacherManager.createTeacher();
            missionManager.openMission(player,teacher);
            return true;
        }
        return false;

    }

    public int getMenuItem(int range){
        int n;
        Scanner in = new Scanner(System.in);

        do{
            System.out.print("Enter menu item: ");
            n = in.nextInt();
        }while(n > range || n < 1);

        return n;
    }

}
