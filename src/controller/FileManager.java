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
	static private final String filePlayersName = "src/data_files/list_of_players.dat";
	static private final String filePathTeacher = "src/data_files/questions/";
	static public final String MALE = "male";
	static public final String FEMALE = "female";
			
	public ArrayList<String> initSurnamesOfTeachers(){
		ArrayList<String> SurnamesOfTeachers = new ArrayList<>(TeacherManager.NUMBER_OF_TEACHERS);
        String fileQuestions = "src/data_files/questions/";
        File tmpDir = new File(fileQuestions);
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
        File tmpDir = new File(filePlayersName);
        if(!tmpDir.exists()){
            if(!createFile(filePlayersName)) System.out.println("Unsuccessful creature");
            return null;
        }else if(tmpDir.length() <= 0) return null;
        try {
            FileInputStream inFile = new FileInputStream(filePlayersName);
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
    
    static public Pair<Pair<String, Boolean>, ArrayList<Question>> readTeacherInfo(String filename) {
    	Pair<String, Boolean> personInfo = new Pair();
    	ArrayList<Question> questions = new ArrayList<>();
    	String name;
    	String strSex;
    	
        Scanner scanner = null;
        File questionsFile = new File(filePathTeacher + filename + ".dat");
        try {
            scanner = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        name = scanner.nextLine();
        strSex = scanner.nextLine();
        personInfo.setFirst(name);
        
        if(strSex.equals(MALE))
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

    static public void saveGame(Vector<Student> players){
        if(players == null)
            return;
        boolean result = true;
        try {
            FileOutputStream outFile = new FileOutputStream(filePlayersName);
            ObjectOutputStream objOutput = new ObjectOutputStream(outFile);
            for (Student one:
                    players) {
                objOutput.writeObject(one);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
    }
}
