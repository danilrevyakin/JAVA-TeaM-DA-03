package model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "health", nullable = false, insertable = false)
    private int health;

    @Column(name = "mana")
    private int mana;

    @Column(name = "level", nullable = false, insertable = false)
    private int level;

    @Column(name = "completed")
    private String completedMissions;

    @Column(name = "score", insertable = false)
    private int score;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    //default constructor
    public Player() {
        health = 50;
        score = 0;
        mana = 50;
        level = 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCompletedMissions() {
        return completedMissions;
    }

    public void setCompletedMissions(String completedMissions) {
        this.completedMissions = completedMissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
