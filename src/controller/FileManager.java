package controller;
import model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Scanner;

public class FileManager {
	static private final String file_players_name = "src/data_files/list_of_players.dat";
	static private final String file_path_teacher = "src/data_files/questions/";
	static public final String MALE = "male";
	static public final String FEMALE = "female";
	
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

    static public HashMap<String, ArrayList<Question>> initQuestions() {

        String teacherName = null;
        HashMap<String, ArrayList<Question>> teacherQuestions = new HashMap<>();
        String file_questions = "src/data_files/questions/";
        File tmpDir = new File(file_questions);
        if (tmpDir.listFiles() != null) {
            for (File questionsFile : tmpDir.listFiles()) {
                ArrayList<Question> questionsAndAnswers = new ArrayList<>();
            Scanner scanner = null;
            try {
                scanner = new Scanner(questionsFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //scanner.useDelimiter("\n");
            teacherName = scanner.nextLine();

            while (true) {
                assert scanner != null;
                if (!scanner.hasNextLine()) break;
                //scanner.useDelimiter("\n");

                    String q = scanner.nextLine();
                    String a = scanner.nextLine();

                    String[] c = new String[4];
                    c[0] = a;
                    for (int i = 1; i < c.length; i++) {
                        if(scanner.hasNextLine())
                            c[i] = scanner.nextLine();
                    }
                    questionsAndAnswers.add(new Question(q, a, c));

            }
            teacherQuestions.put(teacherName, questionsAndAnswers);
            scanner.close();
        }
    }
        return teacherQuestions;
    }
    
    static public Pair<Pair<String, Boolean>, ArrayList<Question>> readTeacherInfo(String filename) {	
    	Pair<String, Boolean> personInfo = new Pair();
    	ArrayList<Question> questions = new ArrayList<>();
    	String name = "";
    	String str_sex = "";
    	
        Scanner scanner = null;
        File questionsFile = new File(file_path_teacher + filename);
        try {
            scanner = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //scanner.useDelimiter("\n");
        name = scanner.nextLine();
        str_sex = scanner.nextLine();
        personInfo.setFirst(name);
        
        if(str_sex == MALE)
        	personInfo.setSecond(Person.MALE);
        else
        	personInfo.setSecond(Person.FEMALE);
        
        while(scanner.hasNextLine()) {
        	questions.add(readQuestion(scanner));
        }
        scanner.close();
        Pair<Pair<String, Boolean>, ArrayList<Question>> teacherInfo = new Pair(personInfo, questions);
    	return teacherInfo;
    }
    
    private static Question readQuestion(Scanner scanner) {
    	String q = "";
    	String a = "";
    	String[] c = new String[4];;
    	assert scanner != null;
        //scanner.useDelimiter("\n");
        q = scanner.nextLine();
        a = scanner.nextLine();
        c[0] = a;
        for (int i = 1; i < c.length; i++) {
            if(scanner.hasNextLine())
                c[i] = scanner.nextLine();
        }   
    	return new Question(q, a, c);
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
