package view;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import model.Mission;
import model.Question;
import model.Student;
import model.Teacher;

public class ConsoleView implements Serializable {
    public boolean open(Mission mission){

        System.out.println("\nWelcome to the " + mission.getMissionNumber() + " mission 8).\n" +
                "Your teacher is " + mission.getTeacher().getName() + " (" + mission.getTeacher().getHealth() + "HP)");

        return true;
    }

    public void getPersonalInfo(Student person){
        System.out.println(person.getName() +  " (level: "+ person.getLevel() +
                "; health: " + person.getHealth() + "; mana: " +  person.getMana() + "): ");
    }
    
    public void YouAlmostWon() {
    	System.out.println("You almost won (");
    }
    //Student data////
    public String setStudentName(){
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
        }while(name == null || name.trim().isEmpty());
        return name;
    }
    public void Teachers_out_of_Index() {
    	System.out.println("Teachers_out_of_Index: maybe class MissionManager method generateMissions");
    }
    private void print(String str) {
    	System.out.println(str);
    }
    public void StatckevichRegards() {
    	print("Yes, that is right. You are well done");
    }
    
    public boolean setStudentSex(){
        Scanner in = new Scanner(System.in);
        boolean sex = true;
        System.out.print("Enter sex(1 - man; 0 - women): ");
        short sexS = in.nextShort();
        if(sexS == 0) sex = false;
        return sex;
    }

    public void has_no_mission() {
    	System.out.println("Sorry, but you has no available mission");
    }

    public String giveAnswer(){
        System.out.print("Enter answer: ");
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        return answer;
    }

    public void choosingMission(Vector<Mission> missions){
    	int size_missions = missions.size();
    	System.out.println();
    	for(Mission mission: missions) {
    		if(mission.mission_available()) {
    			System.out.println("#" + mission.getMissionNumber() + ".\t" + mission.getTeacher().getName() + "\tHealth: " + + mission.getTeacher().getHealth());
    		}
    	}
    	System.out.print("#0.\tExit");
        System.out.print("\nChoose mission number: ");
    }
    
    public int missinNumScanner(int missinNum){
        Scanner in = new Scanner(System.in);
        missinNum = in.nextInt();
        return missinNum;
    }

    public void quiz(Question question){
        System.out.println("Your task: " + question.getQuestion());
        System.out.println("Choices: ");
        for (String choice : question.getChoices()) {
            System.out.println("* " + choice);
        }
        System.out.print("\n");
    }

    public void correctAnswerOutput(Teacher teacher){
        System.out.println("Correct! Now " + teacher.getName() + " has " + teacher.getHealth() + "HP");
    }

    public void tryAgain(int hp){
        System.out.println("Wrong, now your health is " + hp+"...");
    }

    public void studentDefeat(){
        System.out.println("You were defeated by the boss! See you at the session...");
    }

    public void helloFriend(){
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }

    public void victory(){
        System.out.println("VICTORY!!!");
    }

    public void teacherDefeat(){
        System.out.println("OMG, You're correct...again... My colleagues will take revenge!");
    }
    public void missionCompleted(){
        System.out.println("Mission Completed!");
    }
}
