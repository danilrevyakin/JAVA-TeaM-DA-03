package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "questions_answers")
public class Question implements Serializable{
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "option1", nullable = false)
    private String option1;

    @Column(name = "option2", nullable = false)
    private String option2;

    @Column(name = "option3", nullable = false)
    private String option3;

    @Column(name = "option4", nullable = false)
    private String option4;

    @Column(name = "teacher_id", nullable = false)
    private int teacherID;

    @Column(name = "answer", nullable = false)
    private String answer;

    public Question(String question, String option1, String option2, String option3, String option4, String answer, int teacherID){
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.teacherID = teacherID;
    }

    public Question() {}

    //getters


    public int getTeacherID() {
        return teacherID;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

    public ArrayList<String> getChoices(){
        ArrayList<String> choices = new ArrayList<>(List.of(option1, option2,option3,option4));
        Collections.shuffle(choices);
        return choices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id =" + id +
                ", question ='" + question + '\'' +
                ", option1 ='" + option1 + '\'' +
                ", option2 ='" + option2 + '\'' +
                ", option3 ='" + option3 + '\'' +
                ", option4 ='" + option4 + '\'' +
                "}\n";
    }
}