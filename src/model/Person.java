package model;

import java.io.Serializable;

public class Person implements Serializable {
    private int mana;
    private String name;
    private boolean sex;
    private int level;
    private int maxMana;
    private int maxLevel;

    //getters & setters

    public int getMana(){
        return mana;
    }

    public void setMana(int mana){
        if(mana < 0) this.mana = 0;
        this.mana = Math.min(mana, maxMana);
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        if(level <= 0) System.out.println("We can't lose level!!");
        else
            this.level = level;
    }

    public int getMaxLevel(){
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel){
        if(maxLevel <= 0) System.out.println("Level can't be less than 1");
        else
            this.maxLevel = maxLevel;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getSex(){
        return sex;
    }

    public void setSex(boolean sex){
        this.sex = sex;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public void setMaxMana(int maxMana){
        this.maxMana = maxMana;
    }
}
