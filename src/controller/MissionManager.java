package controller;
import model.*;
import view.*;
import java.util.ArrayList;
import java.util.Vector;

public class MissionManager {

    private final ConsoleView consoleView = new ConsoleView();
    private final FileManager fileManager = new FileManager();
    private final StudentManager studentManager = new StudentManager();

    public  Mission createMission(Student student, int missionsNum, Teacher teacher){
        Mission newMission = new Mission(student, teacher, missionsNum);
        ArrayList<Questions> questionSet = fileManager.initQuestions();
        int nN = (int)(Math.random()*questionSet.size());
        newMission.setQuestion(questionSet.get(nN).getQuestion());
        newMission.setAnswer(questionSet.get(nN).getAnswer());
        newMission.setChoices(questionSet.get(nN).getChoices());
        questionSet.remove(nN);

        return newMission;
    }

    public Vector<Mission> generateMissions(Student student, Teacher teacher){
        Vector<Mission> missions = new Vector<>(student.getLevel() + 1);
        for(int i = 0; i < student.getMaxLevel(); i++){
            missions.add(createMission(student, student.getLevel() + i, teacher));
        }
        return missions;
    }

    public boolean startMission(Student student, Mission mission, Teacher teacher){
        consoleView.open(mission,teacher);
        final int MAX_ATTEMPT = 3;
        int attempt = 0;
        String studentAnswer;
        consoleView.quiz(mission);

        while(attempt < MAX_ATTEMPT) {
            studentAnswer = studentManager.giveAnswer();
            if (studentAnswer.toLowerCase().equals(mission.getAnswer())) {
                consoleView.correctAnswer();
                student.setMana(student.getMana() + 10);
                student.score += 10;
                teacher.setMana(teacher.getMana() - 10);
                return true;
            } else {
                if(attempt == 2) consoleView.defeat();
                else consoleView.tryAgain();
                student.setMana(student.getMana() - 5);
                student.score -= 5;
            }
            attempt++;
        }
        return false;
    }

    public void openMission(Student student, Teacher teacher){
        int missinNumber = 0;
        Vector<Mission> missions = generateMissions(student,teacher);

        while (missinNumber <= 0 || missinNumber > missions.size()){
            consoleView.choosingMission(missions);
            missinNumber = consoleView.missinNumScanner(missinNumber);
        }

        startMission(student, missions.get(missinNumber - 1), teacher);
    }
}
