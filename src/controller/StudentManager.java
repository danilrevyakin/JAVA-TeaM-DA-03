package controller;
import model.*;
import java.util.Scanner;

public class StudentManager {

    public Student createStudent(){
        String name;
        boolean sex = true;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Enter name: ");
            name = in.nextLine();
        }while(name == null || name.trim().isEmpty());

        System.out.print("Enter sex(1 - man; 0 - women): ");
        short sexS = in.nextShort();
        if(sexS == 0) sex = false;
        return new Student(name, sex);
    }

    public String giveAnswer(){
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();

        return answer;
    }
}
