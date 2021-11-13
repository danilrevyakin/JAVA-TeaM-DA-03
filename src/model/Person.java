package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private boolean sex;
    private int health;

    public void correctStudentAnswer(){}

    public void wrongStudentAnswer(){}

    //getters & setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSex(boolean sex){
        this.sex = sex;
    }
}
