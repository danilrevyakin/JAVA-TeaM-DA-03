package view;

import model.Teacher;

import java.io.Serializable;
import java.util.Scanner;

public class ConsoleView implements Serializable {
    public String giveAnswer() {
        System.out.print("Enter answer: ");
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        return answer;
    }

    public void correctAnswerOutput(Teacher teacher) {
        System.out.println("Correct! Now " + teacher.getName() + " has " + teacher.getHealth() + "HP");
    }

    public String tryAgain(int hp) {
        return ("Wrong, now your health is " + hp + "...");
    }

    public String studentDefeat() {
        return ("You were defeated by the boss! See you at the session...");
    }

    public String victory() {
        return "VICTORY!!!";
    }

    public String teacherDefeat() {
        return ("OMG, You're correct...again... My colleagues will take revenge!");
    }

    //Skills view
    public boolean TeacherSkillMessage(String msg, String correctAnsw, String wrongAnsw) {
        System.out.println(msg + " [y/n]: ");
        Scanner in = new Scanner(System.in);
        String answ = in.nextLine();
        if (answ.equals("y")) {
            System.out.println(correctAnsw);
            return true;
        }
        System.out.println(wrongAnsw);
        return false;
    }

    public boolean BokhonovSkillMessage(){
        final int correctAnsw = 1915;
        int count = 0;
        System.out.println("Ok, I have additional question for you: What year was Richter born? You have 10 tries: ");
        Scanner in = new Scanner(System.in);
        int answ = in.nextInt();
        while ((answ != correctAnsw) && count < 10){
            System.out.println("Wrong!!!!!");
            answ = in.nextInt();
            count++;
        }
        if(answ == correctAnsw){
            System.out.println("Absolutely right, You deserve +10 extra points!");
            return true;
        }
        System.out.println();
        return false;
    }





    static public String wrongMessage(){
        return "You are wrong";
    }
    static public String correctMessage(){
        return "You are correct";
    }
}
