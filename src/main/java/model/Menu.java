package model;
import java.util.LinkedList;

public class Menu { 
    public item newGame;
    public item selectGame;
    public item quit;
    public static LinkedList<item> Items;

    public Menu(){
        newGame = new item(1, "New Game");
        selectGame = new item(2, "Select saved game");
        quit = new item(3, "Quit game");
        Items = new LinkedList<>();
        Items.add(newGame);
        Items.add(selectGame);
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