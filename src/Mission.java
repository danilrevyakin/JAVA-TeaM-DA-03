import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Mission {
    private int missionNumber;
    private final Teacher teacher;
    private final Student student;
    private boolean isOpen;
    private String question;
    private String answer;
    private ArrayList<String> choices;

    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        this.student = student;
    }

    //setters

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setChoices(ArrayList<String> choices){
        this.choices = choices;
    }

    //methods
    public boolean open(){

            System.out.println("Welcome to the " + missionNumber + " mission 8).\n" +
                    "Your teacher is " + teacher.getName());
            isOpen = true;

        return isOpen;
    }

    public boolean startMission(){
        student.enterMission(this);
        int maxAttempt = 3;
        int attempt = 0;
        String studentAnswer;
        System.out.println("Your task: " + question);
        System.out.print("Choices: ");
        for (String choice : choices) {
            System.out.print(choice + "  ");
        }
        while(attempt < maxAttempt) {
            System.out.print("\n>> ");
            studentAnswer = student.giveAnswer();
            if (studentAnswer.toLowerCase().equals(answer)) {
                System.out.println("Correct!!");
                student.setMana(student.getMana() + 10);
                student.score += 10;
                teacher.setMana(teacher.getMana()-10);
                return true;
            } else {
                if(attempt == 2) System.out.println("You lose (");
                else System.out.println("Noooo( Try again: ");
                student.setMana(student.getMana() - 5);
                student.score -= 5;
            }
            attempt++;
        }
        return false;
    }
}
