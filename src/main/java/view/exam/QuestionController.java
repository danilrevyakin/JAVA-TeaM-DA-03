package view.exam;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Question;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class QuestionController {

    @FXML
    private VBox vBox;

    @FXML
    private HBox questionContainer;

    @FXML
    private Label questionText;

    @FXML
    private Separator separatorBetweenQuestionAndCheckBoxes;
    private String currentAnswer = null;
    private String choicesStyleCSS = getClass().getResource("radioButtonStyle.css").toExternalForm();

    private SeparatorFactory factory = new SeparatorFactory();
    private ShapeFactory shapeFactory = new ShapeFactory();
    private LinkedList<RadioButton> radioButtons = new LinkedList<>();
    private ToggleGroup group;

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    private VBox getChoices(Question question){
        VBox vBoxForChoices = new VBox();
        group = new ToggleGroup();
        for (String variant : question.getChoices()) {
            RadioButton radioButton = new RadioButton(variant);
            Separator separator = factory.getSeparator(Orientation.VERTICAL, 10, false);
            radioButtons.add(radioButton);
            vBoxForChoices.getStylesheets().add(choicesStyleCSS);
            radioButton.setToggleGroup(group);
            vBoxForChoices.getChildren().add(separator);
            vBoxForChoices.getChildren().add(radioButton);
        }
        return vBoxForChoices;
    }
    public void setQuestion(Question question) {
        currentAnswer = null;
        questionText.setText(question.getQuestion());
        HBox hBox = new HBox();
        Separator hSeparator = factory.getSeparator(Orientation.HORIZONTAL, 20, false);
        hBox.getChildren().add(hSeparator);
        VBox vBoxForChoices = getChoices(question);
        hBox.getChildren().add(vBoxForChoices);
        vBox.getChildren().add(hBox);
        setActions();
    }

    private void setActions(){
        radioButtons.forEach(x->{
            x.setOnAction(actionEvent -> {
                currentAnswer = x.getText();
            });
        });
    }

    public void setDisabledCurrentChoices(){
        radioButtons.forEach(button->{
            button.setDisable(true);
        });
    }

    public VBox getVBox() {
        return vBox;
    }
}
