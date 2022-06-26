package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Column(name = "options", nullable = false)
    private String options;

    @Column(name = "teacher_id", nullable = false)
    private int teacherID;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Transient
    private List<String> choices;

    public Question(String question, String options, int teacherID, String answer){
        this.question = question;
        this.options = options;
        this.choices = Arrays.stream(options.split("//")).toList();
        this.answer = answer;
        this.teacherID = teacherID;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setChoices(ArrayList<String> variants){
        if(variants.size() < 1){
            return;
        }
        this.choices = variants;
    }

    public Question() {}

    //getters
    public int getTeacherID() {
        return teacherID;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){return answer;}

    public ArrayList<String> getChoices() {
        ArrayList<String> variants;
        if (options != null) {
            variants = new ArrayList<>(Arrays.asList(options.split("//")));
            Collections.shuffle(variants);
            return variants;
        }
        else{
            return (ArrayList<String>) choices;
        }
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }
}