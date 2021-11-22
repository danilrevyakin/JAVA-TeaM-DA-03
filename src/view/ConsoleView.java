package view;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import model.Mission;
import model.Question;
import model.Student;
import model.Teacher;

public class ConsoleView implements Serializable {
    public boolean open(Mission mission) {

        System.out.println("\nWelcome to the " + mission.getMissionNumber() + " mission 8).\n" +
                "Your teacher is " + mission.getTeacher().getName() + " (" + mission.getTeacher().getHealth() + "HP)");

        return true;
    }

    public void getPersonalInfo(Student person) {
        System.out.println(person.getName() + " (level: " + person.getLevel() +
                "; health: " + person.getHealth() + "; mana: " + person.getMana() + "): ");
    }

    public void YouAlmostWon() {
        System.out.println("You almost won (");
    }

    //Student data////
    public String setStudentName() {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
        } while (name == null || name.trim().isEmpty());
        return name;
    }

    public void Teachers_out_of_Index() {
        System.out.println("Teachers_out_of_Index: maybe class MissionManager method generateMissions");
    }


    public boolean setStudentSex() {
        Scanner in = new Scanner(System.in);
        boolean sex = true;
        System.out.print("Enter sex(1 - man; 0 - women): ");
        short sexS = in.nextShort();
        if (sexS == 0) sex = false;
        return sex;
    }

    public void has_no_mission() {
        System.out.println("Sorry, but you has no available mission");
    }

    public String giveAnswer() {
        System.out.print("Enter answer: ");
        String answer;
        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        return answer;
    }

    public void choosingMission(Vector<Mission> missions) {
        //int size_missions = missions.size();
        System.out.println();
        for (Mission mission : missions) {
            if (mission.missionAvailable()) {
                System.out.println("#" + mission.getMissionNumber() + ". " + mission.getTeacher().getName() + "\t\tHealth: " + +mission.getTeacher().getHealth());
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

    public void tryAgain(int hp) {
        System.out.println("Wrong, now your health is " + hp + "...");
    }

    public void studentDefeat() {
        System.out.println("You were defeated by the boss! See you at the session...");
    }

    public void helloFriend() {
        System.out.println("Hello, friend! Welcome to \"IASA\" game.\nToday you.....(instruction)");
    }

    public void victory() {
        System.out.println("VICTORY!!!");
    }

    public void teacherDefeat() {
        System.out.println("OMG, You're correct...again... My colleagues will take revenge!");
    }

    public void missionCompleted() {
        System.out.println("Mission Completed!");
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
        System.out.println("Absolutely wrong, I want to see you on the commission faster, so I'll take off half of your health");
        return false;
    }

    public void VerbitskiySkillMessage(){
        System.out.println("Oh, sorry, I didn't hear the question, please answer again");
    }

    public void HighSkillCorrectMessage(int k){
        System.out.println("I see you were preparing, +2 points to your score!");
    }
    public void HighSkillWrongMessage(int k){
        System.out.println("You answer somehow uncertainty\nScore "+k);
    }

    public void RomanovSkillCorrectMessage(int k){
        System.out.println("Add " + k + " idinichock to your score!");
    }
    public void RomanovSkillWrongMessage(int k){
        System.out.println("Lowering your mana by a couple of dozen\nMana "+k);
    }

    public void SnizhkoSkillCorrectMessage(int k){
        System.out.println("I like you, add " + k + " points to your score!");
    }
    public void SnizhkoSkillWrongMessage(int k){
        System.out.println("You should study more\nHealth " + k + "; Mana " + k + ";");
    }

    public void StatkevichSkillCorrectMessage(int score, int mana, int health){
        System.out.println("As always correct, I will pamper you with additional points!\nScore " + score +
                "; Health " + health + "; Mana " + mana);
    }
    public void StatkevichSkillWrongMessage(int k){
        System.out.println("I don't even know what grade to give you!\nScore " + k + ";");
    }

    public void StikanovSkillCorrectMessage(int score, int health){
        System.out.println("You are unsure of the answer!\nScore " + score +
                "; Health " + health );
    }
    public void StikanovSkillWrongMessage(){
        System.out.println("Professionals don't answer that way\nReduce your health and points by 15%");
    }

    public void teacherAngry(int k){
        System.out.println("Omg, very stupid mistake, you pissed me off, I will give you a bad grade!\n" +
                "Health - " + k +"; Score - " + k + ";");
    }

    public void teacherHappy(int k){
        System.out.println("I am in a good mood today, I will add a " + k + " points for you!");
    }
}
