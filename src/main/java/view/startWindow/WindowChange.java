package view.startWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowChange {
    public static void openNewScene(String path, Scene scene, String className) {
        scene.getWindow().hide();
        URL url;
        FXMLLoader loader = null;
        try {
            url = Class.forName(className).getResource(path);
            loader = new FXMLLoader(url);
            loader.load();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
