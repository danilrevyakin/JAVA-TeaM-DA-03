package model;

import view.ConsoleView;

public class Teacher extends Person {
    private int DEFAULT_HP = 50;
    private final ConsoleView consoleView = new ConsoleView();

    public Teacher(String name){
        setName(name);
        setHealth(DEFAULT_HP);
    }

    public void correctStudentAnswer(){
        setHealth(getHealth() - 25);

        if(getHealth() <= 0){
            consoleView.teacherDefeat();
        }
    }

    public void wrongStudentAnswer(){
        setHealth(getHealth() + 5);
    }
}
