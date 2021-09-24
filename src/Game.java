import java.util.Scanner;

public class Game {

    String[][] teachersNames = {{"Y. Bokhonov", "V. Statckevich", "B. Snizhko", "O. Beznosic", "V. Romanov;"},
                                {"V. Stickanov", "V. Artuhov", "A. Verbitskiy", "V. High"}};
    String[][] questions = {{"x^2 + 2x + 1 = 0", "log2(256)", "lim(x+2/x) (x -> 0)"},
                            {"2dx + 2dy = 5", "d(ln(7x))/dx"}};

    public Student createStudent(){
        String name;
        boolean sex;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Enter name: ");
            name = in.nextLine();
        }while(name == null || name.trim().isEmpty());

        System.out.println("Enter sex(1 - man; 0 - women): ");
        sex = in.nextBoolean();

        return new Student(name, sex);
    }

    public Teacher createTeachers(Student student){
       int nN = (int) (Math.random() * teachersNames[student.getLevel()].length);
       int nQ = (int) (Math.random() * questions[student.getLevel()].length);
       Teacher teacher = new Teacher(teachersNames[student.getLevel()][nN]);

       return teacher;
    }
}
