import java.util.Locale;
import java.util.Scanner;

public class Mission {
    private int missionNumber;
    private Teacher teacher;
    private Student student;
    private boolean isOpen;
    private String question;
    private String answer;

    public Mission(Student student, Teacher teacher, int missionNumber){
        this.missionNumber = missionNumber;
        this.teacher = teacher;
        this.student = student;
    }

    public boolean open(){
        if(student.getLevel() >= missionNumber){
            System.out.println("Welcome to the " + missionNumber + " mission 8).\n" +
                    "Your teacher is " + teacher.getName());
            isOpen = true;
        }
        else{
            System.out.println("Your level is too low for this mission 8(");
            isOpen = false;
        }
        return isOpen;
    }

    public boolean startMission(){
        int maxAttempt = 3;
        int attempt = 0;
        String studentAnswer;
        System.out.println("Your task: " + question);
        while(attempt < maxAttempt) {
            studentAnswer = student.giveAnswer();
            if (studentAnswer.toLowerCase().equals(answer)) {
                System.out.println("Correct!!");
                student.setMana(student.getMana() + 10);
                return true;
            } else {
                System.out.println("Noooo( Try again");
                student.setMana(student.getMana() - 5);
            }
            attempt++;
        }
        return false;
    }
}
