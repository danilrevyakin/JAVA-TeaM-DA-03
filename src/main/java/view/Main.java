package view;

import controller.hibernateUtil.UserDao;
import view.startWindow.AuthorizationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args){launch();}

    @Override
    public void start(Stage stage) throws Exception {
        URL url = AuthorizationController.class.getResource("hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Escape from IASA");
        stage.setScene(scene);
        stage.show();
    }
}
