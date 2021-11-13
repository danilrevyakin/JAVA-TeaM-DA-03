package controller;
import model.*;
import view.ConsoleView;

import java.util.Scanner;

public class StudentManager {

    private final ConsoleView consoleView = new ConsoleView();

    public Student createStudent(){
        return new Student(consoleView.setStudentName(), consoleView.setStudentSex());
    }

    public String giveAnswer(){
        return consoleView.giveAnswer();
    }

}
