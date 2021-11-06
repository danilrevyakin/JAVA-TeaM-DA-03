import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;

public class Game {
    private static ArrayList<Questions> questionSet;
    private static Vector<String> teacherSet;

    public static Student createStudent(){
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


    public static Teacher createTeacher() throws IOException {
        teacherSet = FileManager.initTeachers();
       int nN = (int) (Math.random() * teacherSet.size());
       return new Teacher(teacherSet.get(nN));
    }

    public static Mission createMission(Student student, int missionsNum) throws IOException {
        Teacher teacher = createTeacher();
        Mission newMission = new Mission(student, teacher, missionsNum);
        questionSet = FileManager.initQuestions();
        int nN = (int)(Math.random()*questionSet.size());
        newMission.setQuestion(questionSet.get(nN).getQuestion());
        newMission.setAnswer(questionSet.get(nN).getAnswer());
        newMission.setChoices(questionSet.get(nN).getChoices());
        questionSet.remove(nN);

        return newMission;
    }
}
