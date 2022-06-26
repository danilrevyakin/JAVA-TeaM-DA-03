package view.exam;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;

public class ControllerFactory {

    private FXMLLoader fxmlLoader;
    private Pane pane;

    private Object controller;

    private final String pathToFXML;
    private final Object object;

    public ControllerFactory(String pathToFXML, Object object) {
        this.pathToFXML = pathToFXML;
        this.object = object;
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(object.getClass().getResource(pathToFXML));
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
