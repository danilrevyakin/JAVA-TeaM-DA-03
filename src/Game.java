import java.util.Scanner;

public class Game {

    private  static String[][] teachersNames = {{"Y. Bokhonov", "V. Statckevich", "B. Snizhko", "O. Beznosic", "V. Romanov;"},
                                {"V. Stickanov", "V. Artuhov", "A. Verbitskiy", "V. High"}};
    private  static String[][] questions = {{"x^2 + 2x + 1 = 0", "log2(256)", "lim(x+2/x) (x -> +inf)"},
                            {"2dx + 2dy = 5", "d(ln(7x))/dx"}};
    private  static String[][] answers =  {{"-1", "8", "1"},
            {"dy = 5", "1/7x"}};


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
       Teacher teacher = new Teacher(teachersNames[student.getLevel()-1][nN]);
       return teacher;
    }

    public static Mission createMissions(Student student){
        Teacher teacher = createTeacher(student);
        Mission newMission = new Mission(student, teacher, student.getLevel());
        int nN = (int)Math.random()*questions[student.getLevel()-1].length;
        newMission.setQuestion(questions[student.getLevel()-1][nN]);
        newMission.setAnswer(answers[student.getLevel()-1][nN]);

        return newMission;
    }
}
