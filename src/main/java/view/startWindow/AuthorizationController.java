package view.startWindow;

import controller.factory.MissionFactory;
import controller.hibernateUtil.PlayerDao;
import controller.hibernateUtil.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Student;
import model.User;
import view.animations.Shake;
import view.map.StaticMapController;

import java.io.IOException;

public class AuthorizationController{
    @FXML
    private Button authButton;

    @FXML
    private Button loggingSignUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loggingStatisticsButton;

    UserDao userDao;
    PlayerDao playerDao;


    public AuthorizationController() {
        userDao = new UserDao();
        playerDao = new PlayerDao();
    }

    @FXML
    void initialize() {
        authButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if (!loginText.isEmpty() && !loginPassword.isEmpty()){
                loginUser(loginText,loginPassword);
            }else{
                playAnimation();
            }
        });

        loggingStatisticsButton.setOnAction(event -> WindowChange.openNewScene(
                "statistics.fxml",
                loggingStatisticsButton.getScene(),
                StatisticsController.class.getName()
        ));

        loggingSignUpButton.setOnAction(event -> WindowChange.openNewScene(
                "signUp.fxml",
                loggingSignUpButton.getScene(),
                RegistrationController.class.getName()));

    }

    private void loginUser(String loginText, String loginPassword){
        User user = userDao.findByLogin(loginText);

        if (user != null && user.getPassword().equals(loginPassword)){
            try {
                Stage stage = (Stage) authButton.getScene().getWindow();
                stage.close();
                Student student = new Student(user.getPlayer(), user);
                MissionFactory factory = new MissionFactory();
        		factory.generateMissions(student);
                StaticMapController.playMapInGUI(student);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            playAnimation();
        }
    }

    private void playAnimation(){
        Shake userLoginAnim = new Shake(loginField);
        Shake userPassAnim = new Shake(passwordField);
        userLoginAnim.playAnim();
        userPassAnim.playAnim();
    }
}
