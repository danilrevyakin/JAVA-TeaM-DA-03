package view.exam.grapchicFactory;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import view.exam.ExamController;

import java.util.function.Consumer;

public class ButtonsDirector {
    private static final String pathToExitPhoto = "EscapeFromIASA.png";

    static public Button createManaButton(Consumer<ActionEvent> consumer) {
        ButtonsBuilder builder = new ButtonsBuilder(consumer, 120, 75);
        builder.setStyle("src/main/resources/view/exam/ManaButton.txt");
        builder.setText("Use your mana");
        return builder.getButton();
    }

    public static Button createExitButton(Consumer<ActionEvent> consumer) {
        ButtonsBuilder builder = new ButtonsBuilder(consumer, 150, 100);
        builder.setImage(pathToExitPhoto, ExamController.class);
        return builder.getButton();
    }
}
