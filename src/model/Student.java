package model;
import java.io.Serializable;

public class Student extends Person implements Serializable {
    private final int MAX_MANA = 100;
    private final int MANA = 50;
    private final int LEVEL = 1;
    public int score = 0;

    public Student(){
        setMaxMana(MAX_MANA);
        setMana(MANA);
        setLevel(LEVEL);
    }

    public Student(String name, boolean sex){
        setMaxMana(MAX_MANA);
        setMana(MANA);
        setLevel(LEVEL);
        setName(name);
        setSex(sex);
        setMaxLevel(2);
    }
}
