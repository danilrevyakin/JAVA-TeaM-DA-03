package controller;

import model.Student;
import view.ConsoleView;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager  {
	private final ConsoleView consoleView = new ConsoleView();

    public String giveAnswer(){
        return consoleView.giveAnswer();
    }

	public void getAvailableMissions(Student student){
//		MissionManager manager = new MissionManager();
//		manager.generateMissions(student);
		if (student.getPlayer().getCompletedMissions() != null) {

			String[] completed = student.getPlayer().getCompletedMissions().split(",");
			for (String s : completed) {
				int value = Integer.parseInt(s);
				student.missions.removeIf(mission -> mission.getTeacher().getId() == value);
				student.decreaseCounterAvailableMissions();
			}
		}
	}

}
