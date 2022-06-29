package view.startWindow;

import controller.factory.MissionFactory;
import controller.hibernateUtil.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Player;
import model.Student;
import model.User;
import view.map.StaticMapController;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {
    @FXML
    private Label emailWarning;

    @FXML
    private Label loginWarning;

    @FXML
    private Label nameWarning;

    @FXML
    private Label passwordWarning;

    @FXML
    private TextField signUpEmail;

    @FXML
    private CheckBox signUpFemale;

    @FXML
    private TextField signUpLogin;

    @FXML
    private CheckBox signUpMale;

    @FXML
    private TextField signUpName;

    @FXML
    private CheckBox signUpOther;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpRegButton;

    @FXML
    private TextField signUpSurname;

    @FXML
    private Label surnameWarning;

    @FXML
    private Label signUpSexWarning;

    @FXML
    Button signUpGoBackButton;

    private static Properties regexProperties;
    private static Properties warningProperties;
    private boolean isCorrect;

    public RegistrationController() {
        regexProperties = new Properties();
        warningProperties = new Properties();
        isCorrect = true;
        FileReader reader;

        try {
            reader = new FileReader("src/main/resources/regex.properties");
            regexProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new FileReader("src/main/resources/warnings.properties");
            warningProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum Type {
        name,
        surname,
        login,
        password,
        sex,
        email
    }

    @FXML
    void initialize() {
        signUpRegButton.setOnAction(event -> {
            processUser();
            isCorrect = true;
        });

        signUpGoBackButton.setOnAction(event -> WindowChange.openNewScene(
                "hello-view.fxml",
                signUpGoBackButton.getScene(),
                AuthorizationController.class.getName()
        ));

    }

    public void processUser() {
        String firstName = signUpName.getText();
        checkRegex(Type.name, firstName, nameWarning, warningProperties.getProperty("name"));

        String lastName = signUpSurname.getText();
        checkRegex(Type.surname, lastName, surnameWarning, warningProperties.getProperty("surname"));

        String email = signUpEmail.getText();
        checkRegex(Type.email, email, emailWarning, warningProperties.getProperty("email"));

        String login = signUpLogin.getText();
        checkRegex(Type.login, login, loginWarning, warningProperties.getProperty("login"));

        String password = signUpPassword.getText();
        checkRegex(Type.password, password, passwordWarning, warningProperties.getProperty("password"));

        String sex = setSex();

        if (checkExistence(Type.login, login) || checkExistence(Type.email, email)) {
            isCorrect = false;
        }

        if (isCorrect) {
            User user = new User(firstName, lastName, sex, email, login, password);
            MissionFactory manager = new MissionFactory();
            Student student = createStudent(user);
            manager.generateMissions(student);
            Stage stage = (Stage) signUpRegButton.getScene().getWindow();
            stage.close();
            StaticMapController.playMapInGUI(student);
        }
    }

    private Student createStudent(User user) {
        UserDao userDao = new UserDao();
        Player player = new Player();
        user.setPlayer(player);
        player.setUser(user);
        userDao.save(user);
        try {
            return new Student(player, user);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkRegex(Type type, String input, Label label, String warning) {
        Pattern pattern = Pattern.compile(regexProperties.getProperty(type.name()));
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            label.setText(warning);
            label.setTextFill(Color.color(1, 0, 0));
            isCorrect = false;
        } else {
            label.setText("");
        }
    }

    private boolean checkExistence(Type type, String input) {
        UserDao userDao = new UserDao();
        List<String> emails = userDao.findEmails();
        List<String> logins = userDao.findLogins();

        if (logins.isEmpty()) return false;

        if (type == Type.email && emails.contains(input)) {
            emailWarning.setText(warningProperties.getProperty("emailExists"));
            emailWarning.setTextFill(Color.color(1, 0.4, 0));
            return true;
        } else if (type == Type.login && logins.contains(input)) {
            loginWarning.setText(warningProperties.getProperty("loginExists"));
            loginWarning.setTextFill(Color.color(1, 0.4, 0));
            return true;
        } else return false;
    }


    private String setSex() {
        String sex = null;
        if (signUpMale.isSelected()) {
            sex = "Male";
            signUpSexWarning.setText("");
        } else if (signUpFemale.isSelected()) {
            sex = "Female";
            signUpSexWarning.setText("");
        } else if (signUpOther.isSelected()) {
            sex = "Other";
            signUpSexWarning.setText("");
        } else {
            signUpSexWarning.setText(warningProperties.getProperty("sex"));
            signUpSexWarning.setTextFill(Color.color(1, 0, 0));
            isCorrect = false;
        }
        return sex;
    }
}
