import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    private  static final String[][] teachersNames = {{"Y. Bokhonov", "V. Statckevich", "B. Snizhko", "O. Beznosic", "V. Romanov;"},
                                {"V. Stickanov", "V. Artuhov", "A. Verbitskiy", "V. High"}};

    private  static ArrayList<Questions> questionSet;

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


    public static Teacher createTeacher(Student student){
       int nN = (int) (Math.random() * teachersNames[student.getLevel()-1].length);
       return new Teacher(teachersNames[student.getLevel()-1][nN]);
    }

    public static Mission createMission(Student student, int missionsNum){
        Teacher teacher = createTeacher(student);
        Mission newMission = new Mission(student, teacher, missionsNum);
        questionSet = FileManager.initQuestions();
        int nN = (int)(Math.random()*questionSet.size());
        newMission.setQuestion(questionSet.get(nN).getQuestion());
        newMission.setAnswer(questionSet.get(nN).getAnswer());
        questionSet.remove(nN);

        return newMission;
    }
}
