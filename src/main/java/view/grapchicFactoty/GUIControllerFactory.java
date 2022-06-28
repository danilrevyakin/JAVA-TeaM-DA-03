package view.grapchicFactoty;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIControllerFactory {

    private final FXMLLoader fxmlLoader;
    private final Pane pane;

    private final Object controller;


    public GUIControllerFactory(String pathToFXML, Object objectReference) {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(objectReference.getClass().getResource(pathToFXML));
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        controller = fxmlLoader.getController();
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Pane getPane() {
        return pane;
    }

    public Object getController() {
        return controller;
    }
}
