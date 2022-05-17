package controller;

import hibernateUtil.UserDao;
import model.User;
import view.ConsoleView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {
    User user;
    static Properties properties;
    FileReader reader;

    RegistrationController(User user){
        this.user = user;
        properties = new Properties();

        try {
            reader = new FileReader("src/main/resources/regex.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum Type{
        name,
        surname,
        login,
        password,
        sex,
        email
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);
        user.setName(inputValueWithScanner(sc, Type.name));
        user.setSurname(inputValueWithScanner(sc, Type.surname));
        user.setSex(inputValueWithScanner(sc, Type.sex));
        do {
            user.setEmail(inputValueWithScanner(sc, Type.email));
        }while (checkExistence(Type.email));

        do{
            user.setLogin(inputValueWithScanner(sc, Type.login));
        } while (checkExistence(Type.login));

        user.setPassword(inputValueWithScanner(sc, Type.password));
    }

    private String inputValueWithScanner(Scanner sc, Type type) {
        ConsoleView.printMessage(ConsoleView.INPUT_DATA + type.name() + ": ");
        String input = sc.next();
        Pattern pattern = Pattern.compile(properties.getProperty(type.name()));
        Matcher matcher = pattern.matcher(input);
        while (!matcher.matches()){
            ConsoleView.printMessage(ConsoleView.WRONG_INPUT_DATA);
            ConsoleView.printMessage(ConsoleView.INPUT_DATA + type.name() + ": ");
            input = sc.next();
        }
        return input;
    }

    private boolean checkExistence(Type type){
        UserDao userDao = new UserDao();
        List<String> emails,logins;
        if (type == Type.email) {
             emails = userDao.findAll().stream()
                    .map(User::getEmail)
                    .toList();
             if (emails == null) return false;
             if (emails.contains(user.getEmail())){
                 ConsoleView.printMessage(ConsoleView.EXISTING_EMAIL);
                 return true;
             } else return false;
        } else {
            logins = userDao.findAll().stream()
                    .map(User::getLogin)
                    .toList();
            if (logins == null) return false;
            if (logins.contains(user.getLogin())){
                ConsoleView.printMessage(ConsoleView.EXISTING_LOGIN);
                return true;
            } else return false;
        }
    }
}
