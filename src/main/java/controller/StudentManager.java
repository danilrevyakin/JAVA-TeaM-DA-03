package controller;

import hibernateUtil.UserDao;
import model.Player;
import model.Student;
import model.User;
import view.ConsoleView;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager  {
	private final ConsoleView consoleView = new ConsoleView();
	private final UserDao userDao = new UserDao();

    public Student createStudent() throws IOException {
		User user = new User();
		RegistrationController registrationController = new RegistrationController(user);
		registrationController.processUser();
		Player player = new Player();

		user.setPlayer(player);
		player.setUser(user);

		userDao.save(user);

		return new Student(player, user);
    }

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
