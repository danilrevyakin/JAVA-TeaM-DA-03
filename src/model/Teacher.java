package model;

public class Teacher extends Person {
    private Mission currentMission;
    private final int MAX_MANA = 500;

    public Teacher(String name){
        setMaxMana(MAX_MANA);
        setMana(getMaxMana());
        setName(name);
    }
}
