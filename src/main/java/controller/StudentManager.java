package controller;

import hibernateUtil.UserDao;
import model.Player;
import model.Student;
import model.User;
import view.ConsoleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager  {

    private final ConsoleView consoleView = new ConsoleView();
	private final UserDao userDao = new UserDao();

    public Student createStudent() throws IOException {
		ArrayList<String> info = consoleView.setUserInfo();
		User user = new User(info.get(0), info.get(1), info.get(2), info.get(3),info.get(4), info.get(5));
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
