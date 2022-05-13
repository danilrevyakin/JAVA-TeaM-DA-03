package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String sex;
    private int health;
    public static final boolean MALE = true;
    public static final boolean FEMALE = false;
    
    public Person(String name, String sex, int health) {
    	this.health = health;
    	this.name = name;
    	this.sex = sex;
    }

    public Person() {}

    abstract public void correctStudentAnswer();

    abstract public void wrongStudentAnswer();

    //getters & setters

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public void setSex(String sex){this.sex = sex;}
    
    public String getSex() {return this.sex;}
}
