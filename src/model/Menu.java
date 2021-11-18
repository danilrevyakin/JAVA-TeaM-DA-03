package model;
import java.util.LinkedList;

public class Menu { 
    public item new_game;
    public item select_game;
    public item quit;
    public static LinkedList<item> Items;

    public Menu(){
        new_game = new item(1, "New Game");
        select_game = new item(2, "Select saved game");
        quit = new item(3, "Quit game");
        Items = new LinkedList<>();
        Items.add(new_game);
        Items.add(select_game);
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
}