package view;

import model.Mission;
import model.Question;
import model.Student;
import model.Teacher;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements Serializable {
    public boolean open(Mission mission) {

        System.out.println("\nWelcome to the " + mission.getMissionNumber() + " mission 8).\n" +
                "Your teacher is " + mission.getTeacher().getName() + " (" + mission.getTeacher().getHealth() + "HP)");

        return true;
    }

    public void getPersonalInfo(Student person) {
        System.out.println(person.getUser().getLogin() + " (level: " + person.getLevel() +
                "; health: " + person.getHealth() + "; mana: " + person.getMana() + "): ");
    }

    public String YouAlmostWon() {
        return ("You almost won (");
    }

    public void hasNoMission() {
        System.out.println("Sorry, but you has no available mission");
    }

    public String giveAnswer() {
        System.out.print("Enter answer: ");
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        return answer;
    }

    public void choosingMission(List<Mission> missions) {
        //int size_missions = missions.size();
        System.out.println();
        for (Mission mission : missions) {
            if (mission.missionAvailable()) {
                System.out.println("#" + mission.getMissionNumber() + ". " + mission.getTeacher().getName());
            }
        }
        System.out.print("#0.\tExit");
        System.out.print("\nChoose mission number: ");
    }

    public int missinNumScanner(int missinNum) {
        Scanner in = new Scanner(System.in);
        missinNum = in.nextInt();
        return missinNum;
    }

    public void quiz(Question question) {
        System.out.println("Your task: " + question.getQuestion());
        System.out.println("Choices: ");
        for (String choice : question.getChoices()) {
            System.out.println("* " + choice);
        }
        System.out.print("\n");
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

    public String missionCompleted() {
        return ("Mission Completed!");
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
