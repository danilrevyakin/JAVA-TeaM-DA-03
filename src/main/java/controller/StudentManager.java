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

	public List<Student> sortStudents(List<Student> list){
		return list.stream()
				.sorted(Comparator.comparingInt(Student::getLevel)
						.thenComparingInt(Student::getScore)
						.thenComparingInt(Student::getMana)
						.thenComparingInt(Student::getHealth)
						.reversed())
				.collect(Collectors.toList());
	}
}
