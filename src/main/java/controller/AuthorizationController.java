package controller;

import hibernateUtil.PlayerDao;
import hibernateUtil.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Student;
import model.User;
import view.animations.Shake;

import java.io.IOException;
import java.net.URL;

public class AuthorizationController {

    @FXML
    private Button authButton;

    @FXML
    private Button loggingSignUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    UserDao userDao;
    PlayerDao playerDao;
    GameManager gameManager;

    public AuthorizationController() {
        userDao = new UserDao();
        playerDao = new PlayerDao();
        gameManager = new GameManager();
    }

    @FXML
    void initialize() {
        authButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }
        });

        openNewScene("signUp.fxml");

    }

    private void loginUser(String loginText, String loginPassword){
        User user = userDao.findByLogin(loginText);

        if (user != null && user.getPassword().equals(loginPassword)){
            try {
                gameManager.selectCreatedPlayer(new Student(user.getPlayer(), user));
                //openNewScene(); ТУТ МАЄ БУТИ .fxml МАПИ
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Shake userLoginAnim = new Shake(loginField);
            Shake userPassAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    public void openNewScene(String window){
        loggingSignUpButton.setOnAction(event -> {
            loggingSignUpButton.getScene().getWindow().hide();
            URL url = RegistrationController.class.getResource(window);
            FXMLLoader loader = new FXMLLoader(url);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}
