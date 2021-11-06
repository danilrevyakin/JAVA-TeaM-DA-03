import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;

public class FileManager {
    static public String file_players_name = "src//student_src//list_of_players.dat";
    static public String file_questions = "src//question_src//list_of_questions.dat";
    static public String file_teachers = "src//teachers_src//list_of_teachers";

    static public Vector<Student> init_old_Players() {
        Vector<Student> players = new Vector<>(30);
        File tmpDir = new File(file_players_name);
        if(!tmpDir.exists()){
            if(!create_file(file_players_name)) System.out.println("Unsuccessful creature");
            return null;
        }else if(tmpDir.length() <= 0) return null;
        try {
            FileInputStream inFile = new FileInputStream(file_players_name);
            ObjectInputStream objInput = new ObjectInputStream(inFile);
            while(true){
                try{
                    Object obj = objInput.readObject();
                    players.add((Student)obj);
                }catch(EOFException e){
                    break;
                }
            }
            //inFile.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return players;
    }

    static public ArrayList<Questions> initQuestions(){
        ArrayList<Questions> questionsAndAnswers = new ArrayList<>();
        File tmpDir = new File(file_questions);
        if(!tmpDir.exists()){
            if(!create_file(file_questions)) System.out.println("Unsuccessful creature");
            return null;
        }else if(tmpDir.length() <= 0) return null;

        Scanner scanner = null;
        try {
            scanner = new Scanner(tmpDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            scanner.useDelimiter("\n");
                String q = scanner.next();
                String a = scanner.next();
                String[] c = new String[4];
                c[0] = a;
                for (int i = 1; i < c.length; i++){
                    c[i] = scanner.next();
                }
                questionsAndAnswers.add(new Questions(q,a,c));
            }
            scanner.close();

        return questionsAndAnswers;
    }

    static public Vector<String> initTeachers() throws IOException {
        int lineCount = (int) Files.lines(Path.of(file_teachers)).count();
        Vector<String> teacherSet = new Vector<>(lineCount);
        File tmpDir = new File(file_teachers);
        if(!tmpDir.exists()){
            if(!create_file(file_teachers)) System.out.println("Unsuccessful creature");
            return null;
        }else if(tmpDir.length() <= 0) return null;

        Scanner scanner = null;
        try {
            scanner = new Scanner(tmpDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()) break;

            scanner.useDelimiter("\n");
            String t = scanner.next();
            teacherSet.add(t);
        }
        scanner.close();

        return teacherSet;
    }

    static private boolean create_file(String name){
        File file = new File(name);
        boolean result = false;
        try {
            result = file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    static public boolean saveGame(Vector<Student> players){
        if(players == null){
            return true;
        }
        boolean result = true;
        try {
            FileOutputStream outFile = new FileOutputStream(file_players_name);
            ObjectOutputStream objOutput = new ObjectOutputStream(outFile);
            for (Student one:
                    players) {
                objOutput.writeObject(one);
            }
            //outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
