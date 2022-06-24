package view.exam;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Question;
import org.hibernate.annotations.Check;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class QuestionController {

    @FXML
    private VBox vBox;

    @FXML
    private Text questionText;

    @FXML
    private Separator separatorBetweenQuestionAndCheckBoxes;
    private String currentAnswer = null;

    private LinkedList<RadioButton> radioButtons = new LinkedList<>();

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public void setQuestion(Question question) {
        questionText.setText(question.getQuestion());
        for (String variant : question.getChoices()) {
            RadioButton radioButton = new RadioButton(variant);
            radioButtons.add(radioButton);
            vBox.getChildren().add(radioButton);
        }
        radioButtons.forEach(x->{
            x.setOnAction(actionEvent -> {
                currentAnswer = x.getText();
                if(!x.isSelected()){
                    x.setSelected(true);
                }
                radioButtons.forEach(y->{
                    if(y != x){
                        y.setSelected(false);
                    }
                });
            });
        });
    }

    public void setDisable(){
        radioButtons.forEach(button->{button.setOnAction(e->{});});
    }

    public VBox getVBox() {
        return vBox;
    }
}
