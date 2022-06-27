package view.map;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Student;
import view.exam.ExamController;

import java.util.Objects;

public class ConfirmBox {
    static boolean answer;
    public static boolean display(String text, Student student, int missionNumber){
        Stage window = new Stage();
        window.getIcons().add(new Image(Objects.requireNonNull(ConfirmBox.class.getResourceAsStream("logo2.png"))));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(text);
        window.setMinWidth(250);
        window.setMinHeight(120);
        window.setResizable(false);
        Label label = new Label();
        label.setText(text);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            if (!student.availableMissions.containsKey(missionNumber)) {
                student.setCurrentMission(student.availableMissions.get(missionNumber));
                ExamController.playMissionInGUI(student);
                System.out.println("Opening");
                window.close();
            }else{
                //smth
            }
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //design elements
        BorderPane bp = new BorderPane();
        HBox buttons = new HBox(20);
        buttons.setAlignment( Pos.CENTER );
        buttons.setSpacing( 10 );
        buttons.getChildren().addAll( yesButton, noButton );
        bp.setCenter( buttons );
        HBox msg = new HBox(10);
        msg.setPadding(new Insets(10));
        msg.setSpacing( 5 );
        msg.getChildren().add(label);
        msg.setAlignment( Pos.CENTER );
        msg.setSpacing(10);
        msg.setStyle("-fx-font-size:15;");
        yesButton.setStyle("-fx-border-color:#fea600; -fx-border:10px; -fx-border-radius: 5px;");
        noButton.setStyle("-fx-border-color:fea600; -fx-border:10px; -fx-border-radius: 5px;");
        bp.setStyle("-fx-font-weight: bold; -fx-background-color:#808080; -fx-color:#000;");

        bp.setTop( msg );
        Scene scene = new Scene(bp);
        window.setScene(scene);
        window.show();
        return answer;
    }
}
