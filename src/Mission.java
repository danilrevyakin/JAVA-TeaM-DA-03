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

    //setters & getters

    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question = question;
    }

    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
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
        while(attempt < maxAttempt) {
            System.out.print(">> ");
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
