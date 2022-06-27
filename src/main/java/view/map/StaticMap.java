package view.map;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StaticMap extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        //Fixed size
        int screenWidth = (int)Screen.getPrimary().getBounds().getWidth() * 3/4;
        int screenHeight = (int)Screen.getPrimary().getBounds().getHeight()* 3/4;
        Scene scene = new Scene(root,screenWidth, screenHeight);
        stage.setResizable(false);
        stage.setTitle("Escape From KPI");
        stage.getIcons().add(new Image("logo2.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
