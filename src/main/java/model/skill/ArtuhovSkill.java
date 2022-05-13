package model.skill;
import model.Student;
import view.ConsoleView;
import java.io.Serializable;
import java.util.Random;

public class ArtuhovSkill implements Skill, Serializable {

    private final ConsoleView consoleView = new ConsoleView();

    private final String message = "Do you have any questions about the assignment?";
    private final String correctAnsw = "I see you are interested in the topic, I will add a couple of points for you!!";
    private final String wrongAnsw = "You never have any questions, perhaps you are not interested, I'll take a couple of points from you!!";

    @Override
    public void studentAnswerCorrect(Student student) {
        if(consoleView.TeacherSkillMessage(message, correctAnsw, wrongAnsw)){
            student.setScore(student.getScore() + 5);
            student.setHealth(student.getHealth() + 10);
        }
        else {
            student.setScore(student.getScore() - 5);
            student.setHealth(student.getHealth() - 5);
        }
    }

    @Override
    public void studentAnswerFalse(Student student) {
        Random random = new Random();
        int k = Math.abs(random.nextInt() % 10);
        consoleView.teacherAngry(k);
        student.setScore(student.getScore() - k);
        student.setHealth(student.getHealth() - k);
        student.setMana(student.getMana() - k);
    }
}
