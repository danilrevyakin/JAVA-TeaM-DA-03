package view.exam.grapchicFactoty;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;

public class ButtonsFactory {
    static public Button createManaButton(Consumer<ActionEvent> consumer) {
        Button button = new Button();
        String style;
        try {
            style = new String(Files.readAllBytes(Paths.get("src/main/resources/view/exam/ManaButton.txt")), StandardCharsets.UTF_8);
            style = style.replace("\n", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setStyle(style);
        button.setText("Use your mana");
        button.setOnAction(e -> consumer.accept(e));
        button.setMinWidth(120);
        button.setMinHeight(50);
        return button;
    }

    public static Button initExitButton(String pathToExitPhoto, Consumer<ActionEvent> consumer, Object objectReference) {
        Image image = new Image(Objects.requireNonNull(objectReference.getClass().getResourceAsStream(pathToExitPhoto)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        Button exitButton = new Button();
        exitButton.setGraphic(imageView);
        exitButton.setOnAction(e -> consumer.accept(e));
        return exitButton;
    }
}
