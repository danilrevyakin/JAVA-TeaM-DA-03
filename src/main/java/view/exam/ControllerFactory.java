package view.exam;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;

public class ControllerFactory {
    static public Pair<Object, Pane> getControllerAndPane(String pathToFXML, Object object){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(object.getClass().getResource(pathToFXML));
        Pane pane;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Object controller = fxmlLoader.getController();
        return new Pair<>(controller, pane);
    }
}
