package controller;

import hibernateUtil.PlayerDao;
import hibernateUtil.UserDao;
import model.Student;
import model.User;
import view.ConsoleView;

import java.io.IOException;
import java.util.Scanner;

public class AuthorizationController {
    UserDao userDao;
    PlayerDao playerDao;

    public AuthorizationController() {
        userDao = new UserDao();
        playerDao = new PlayerDao();
    }

    public Student getStudent(){
        Scanner scanner = new Scanner(System.in);
        User user = getUserByLogin(scanner);
        String password;
        do {
            ConsoleView.printMessage(ConsoleView.INPUT_DATA + "password" + ": ");
            password = scanner.nextLine();
        }while (!checkByPassword(user, password));

        try {
            return new Student(playerDao.findById(user.getId()), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserByLogin(Scanner scanner){
        ConsoleView.printMessage(ConsoleView.INPUT_DATA + "login" + ": ");
        String login = scanner.nextLine();
        User user = userDao.findByLogin(login);
        if (user == null){
            ConsoleView.printMessage(ConsoleView.WRONG_LOGIN);
            getUserByLogin(scanner);
        }
        return user;
    }

    private boolean checkByPassword(User user, String password){
        if (user.getPassword().equals(password)){
            return true;
        }else{
            ConsoleView.printMessage(ConsoleView.WRONG_PASSWORD);
            return false;
        }
    }
}
