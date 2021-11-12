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
        String studentAnswer;
        consoleView.quiz(mission);

        while(student.getHealth() > 0) {
            studentAnswer = studentManager.giveAnswer();
            if (studentAnswer.toLowerCase().equals(mission.getAnswer())) {
                student.correctStudentAnswer();
                teacher.correctStudentAnswer();

                if (teacher.getHealth() > 0) consoleView.correctAnswerOutput(teacher);

                return true;
            } else {
                student.wrongStudentAnswer();
                teacher.wrongStudentAnswer();
                return true;
            }
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
