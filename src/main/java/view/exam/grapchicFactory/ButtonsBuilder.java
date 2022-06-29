package view.exam.grapchicFactory;

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

public class ButtonsBuilder {
    private final int width;
    private final int height;

    private final Button button;

    public ButtonsBuilder(Consumer<ActionEvent> consumer, int width, int height) {
        this.width = width;
        this.height = height;
        button = new Button();
        button.setOnAction(consumer::accept);
    }

    public void setStyle(String path) {
        String style = null;
        try {
            style = Files.readString(Paths.get(path));
            style = style.replace("\n", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setStyle(style);
    }

    public void setImage(String path, Class<?> classs) {
        Image image = new Image(Objects.requireNonNull(classs.getResourceAsStream(path)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        button.setGraphic(imageView);
    }

    public void setText(String text) {
        button.setText(text);
    }

    public Button getButton() {
        return button;
    }
}
