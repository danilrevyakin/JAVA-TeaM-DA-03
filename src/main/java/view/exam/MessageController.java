package view.exam;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import view.exam.grapchicFactoty.TextLabelFactory;

public class MessageController {

    @FXML
    private HBox hBox;
    @FXML
    private Separator leftSeparator;

    @FXML
    private Separator rightSeparator;
    @FXML
    private Label text;

    private double labelHeight = 0;

    private final double border = 5;

    private final int maxCharactersInLabelLine = 30;

    public void setText(String stringMessage) {
        TextLabelFactory formatter = new TextLabelFactory(maxCharactersInLabelLine, stringMessage);
        text.setText(formatter.getResult());
        labelHeight = text.getFont().getSize() * formatter.getCounterLinesInLabel() * 2.5;
        text.setMinHeight(labelHeight);
        rightSeparator.setMaxHeight(1);
        leftSeparator.setMaxHeight(1);
        hBox.setMinHeight(getMinHeight());
        hBox.setMinWidth(maxCharactersInLabelLine * 10);
    }

    public double getMinHeight() {
        return labelHeight + border;
    }
}
