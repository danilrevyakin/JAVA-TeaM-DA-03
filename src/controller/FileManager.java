package controller;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import model.Pair;
import model.Person;
import model.Question;
import model.Student;

public class FileManager {
	static private final String file_players_name = "src/data_files/list_of_players.dat";
	static private final String file_path_teacher = "src/data_files/questions/";
	static public final String MALE = "male";
	static public final String FEMALE = "female";
	public ArrayList<String> SurnamesOfTeachers = initNamesOfTeachers();
			
	private ArrayList<String> initNamesOfTeachers(){
		String teacherName = null;
		SurnamesOfTeachers = new ArrayList<>(TeacherManager.NUMBER_OF_TEACHERS);
        String file_questions = "src/data_files/questions/";
        File tmpDir = new File(file_questions);
        if (tmpDir.listFiles() != null) {
            for (File teacherFile : Objects.requireNonNull(tmpDir.listFiles())) {
            	String name = teacherFile.getName();
            	SurnamesOfTeachers.add(name.substring(0, name.length() - 4));
            }
        }
		return SurnamesOfTeachers;
	}

    static public Vector<Student> initOldPlayers() {
        Vector<Student> players = new Vector<>(30);
        File tmpDir = new File(file_players_name);
        if(!tmpDir.exists()){
            if(!createFile(file_players_name)) System.out.println("Unsuccessful creature");
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

//    static public HashMap<String, ArrayList<Question>> initQuestions() {
//
//        String teacherName;
//        HashMap<String, ArrayList<Question>> teacherQuestions = new HashMap<>();
//        String file_questions = "src/data_files/questions/";
//        File tmpDir = new File(file_questions);
//        if (tmpDir.listFiles() != null) {
//            for (File questionsFile : Objects.requireNonNull(tmpDir.listFiles())) {
//                ArrayList<Question> questionsAndAnswers = new ArrayList<>();
//            Scanner scanner = null;
//            try {
//                scanner = new Scanner(questionsFile);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            //scanner.useDelimiter("\n");
//            teacherName = scanner.nextLine();
//
//            while (true) {
//                assert scanner != null;
//                if (!scanner.hasNextLine()) break;
//                //scanner.useDelimiter("\n");
//
//                    String q = scanner.nextLine();
//                    String a = scanner.nextLine();
//
//                    String[] c = new String[4];
//                    c[0] = a;
//                    for (int i = 1; i < c.length; i++) {
//                        if(scanner.hasNextLine())
//                            c[i] = scanner.nextLine();
//                    }
//                    questionsAndAnswers.add(new Question(q, a, c));
//
//            }
//            teacherQuestions.put(teacherName, questionsAndAnswers);
//            scanner.close();
//        }
//    }
//        return teacherQuestions;
//    }
    
    static public Pair<Pair<String, Boolean>, ArrayList<Question>> readTeacherInfo(String filename) {
    	Pair<String, Boolean> personInfo = new Pair();
    	ArrayList<Question> questions = new ArrayList<>();
    	String name;
    	String str_sex;
    	
        Scanner scanner = null;
        File questionsFile = new File(file_path_teacher + filename + ".dat");
        try {
            scanner = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //scanner.useDelimiter("\n");
        assert scanner != null;
        name = scanner.nextLine();
        str_sex = scanner.nextLine();
        personInfo.setFirst(name);
        
        if(str_sex.equals(MALE))
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
    	String q;
    	String a;
    	String[] c = new String[4];
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
    static private boolean createFile(String name){
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
