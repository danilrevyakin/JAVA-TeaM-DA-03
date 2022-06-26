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
import java.util.ArrayList;
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
    private final String choicesStyleCSS;

    private final SeparatorFactory factory = new SeparatorFactory();
    private LinkedList<RadioButton> radioButtons = new LinkedList<>();
    private double labelHeight = 0;
    private final double separatorHeight = 20;
    private final double separatorBetweenChoicesHeight = 10;
    private final double radioButtonHeight = 35;

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public QuestionController() {
        choicesStyleCSS = getClass().getResource("radioButtonStyle.css").toExternalForm();
    }

    private VBox getChoices(Question question){
        VBox vBoxForChoices = new VBox();
        ToggleGroup group = new ToggleGroup();
        for (String variant : question.getChoices()) {
            RadioButton radioButton = new RadioButton(variant);
            Separator separator = factory.getSeparator(Orientation.VERTICAL, separatorBetweenChoicesHeight, false);
            radioButtons.add(radioButton);
            radioButton.setMinHeight(radioButtonHeight);
            vBoxForChoices.getStylesheets().add(choicesStyleCSS);
            radioButton.setToggleGroup(group);
            vBoxForChoices.getChildren().add(radioButton);
            vBoxForChoices.getChildren().add(separator);
        }
        return vBoxForChoices;
    }
    public void setQuestion(Question question) {
        currentAnswer = null;
        String questionLabel = formatString(question.getQuestion());
        questionText.setText(questionLabel);
        labelHeight = questionText.getFont().getSize() * counterLinesInLabel * 2;
        questionText.setMinHeight(labelHeight);
        HBox hBox = new HBox();
        Separator hSeparator = factory.getSeparator(Orientation.HORIZONTAL, separatorHeight, false);
        hBox.getChildren().add(hSeparator);
        VBox vBoxForChoices = getChoices(question);
        hBox.getChildren().add(vBoxForChoices);
        vBox.getChildren().add(hBox);
        vBox.setMinHeight(vBoxHeight());
        setActions();
    }

    private double vBoxHeight(){
        return (separatorBetweenChoicesHeight + radioButtonHeight) * 4 + labelHeight + separatorHeight;
    }

    int maxCharactersInLabelLine = 25;
    int counterLinesInLabel = 1;
    private String formatString(String input){
        counterLinesInLabel = 1;
        if(input.length() < maxCharactersInLabelLine){
            return input;
        }
        StringBuilder stringBuilder = new StringBuilder();
        input = input.replaceAll("\n", ". ");
        String[] strings = input.split(" ");
        int lineLength = 0;
        for(String word : strings){
            if(lineLength + word.length() < maxCharactersInLabelLine){
                stringBuilder.append(" ");
                lineLength++;
            }else {
                stringBuilder.append("\n");
                counterLinesInLabel++;
                lineLength = 0;
            }
            stringBuilder.append(word);
            lineLength += word.length();
        }
        return stringBuilder.toString();
    }
    private void setActions(){
        radioButtons.forEach(x-> x.setOnAction(actionEvent -> currentAnswer = x.getText()));
    }

    public void setDisabledCurrentChoices(){
        radioButtons.forEach(button-> button.setDisable(true));
    }

    public VBox getVBox() {
        return vBox;
    }
}
