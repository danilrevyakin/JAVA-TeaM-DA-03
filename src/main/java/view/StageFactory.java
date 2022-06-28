package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StageFactory {
    private final Object controller;
    private final String pathToFXML;
    private final Class classs;
    private final Stage stage;

    private Parent root;
    public StageFactory(Object controller, String pathToFXML, Class classs) {
        this.controller = controller;
        this.pathToFXML = pathToFXML;
        this.classs = classs;
        this.stage = makeStage();
        setScene();
    }

    public StageFactory(Object controller, String pathToFXML, int screenHeight, int screenWidth, Class classs) {
        this.controller = controller;
        this.pathToFXML = pathToFXML;
        this.classs = classs;
        this.stage = makeStage();
        setScene(screenHeight, screenWidth);
    }

    private Stage makeStage() {
        FXMLLoader loader = null;
        try {
            URL url = classs.getResource(pathToFXML);
            loader = new FXMLLoader(url);
            loader.setControllerFactory(controllerClass -> controller);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root = loader.getRoot();
        Stage stage = new Stage();
        return stage;
    }

    private void setScene(){
        stage.setScene(new Scene(root));
    }

    private void setScene(int screenHeight, int screenWidth){
        stage.setScene(new Scene(root, screenWidth, screenHeight));
    }

    public Stage getStage() {
        return stage;
    }
}
