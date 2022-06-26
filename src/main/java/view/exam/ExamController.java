package view.exam;

import controller.MissionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ExamController implements Initializable {
    @FXML
    private Pane BottomPane;

    @FXML
    private VBox CenterPane;

    @FXML
    private ChoiceBox<String> studentChoices;

    @FXML
    private HBox ReactBox;

    @FXML
    private ScrollPane ScrollChatBox;

    @FXML
    private Button SendButton;

    @FXML
    private StackPane StudentPane;

    @FXML
    private StackPane TeacherPane;

    @FXML
    private Pane TopPane;

    @FXML
    private VBox VboxInScroll;
    private String currentAnswer;

    private final String pathToTeacherPhoto = "oldTeacher.png";
    private final String pathToStudentPhoto = "studentBrown.png";

    private Question question;
    private final ControllerFactory controllerFactory = new ControllerFactory();
    private final Student student;
    private PersonController studentController;
    private final Teacher teacher;
    private PersonController teacherController;
    private final MissionManager missionManager;


    private final SeparatorFactory factory = new SeparatorFactory();

    static public void playMissionInGUI(Mission mission, MissionManager missionManager) {
        URL url = ExamController.class.getResource("Exam.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(controllerClass -> new ExamController(mission.getStudent(), mission.getTeacher(), missionManager));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public ExamController(Student student, Teacher teacher, MissionManager missionManager) {
        this.missionManager = missionManager;
        this.student = student;
        this.teacher = teacher;
    }

    public void getChoice(ActionEvent e){
        currentAnswer = studentChoices.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeacherPane.getChildren().add(getTeacherView(teacher));
        StudentPane.getChildren().add(getStudentView(student));
        studentChoices.setOnAction(this::getChoice);
        updateQuestion();
        VboxInScroll.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, true));
    }



    private MessageController messageController;
    private HBox getMessageView(String stringMessage) {
        Pair<Object, Pane> data = controllerFactory.getControllerAndPane("Message.fxml", this);
        messageController = (MessageController) data.getKey();
        messageController.setText(stringMessage);
        return (HBox) data.getValue();
    }


    private VBox getTeacherView(Teacher teacher) {
        Pair<Object, Pane> data = controllerFactory.getControllerAndPane("Person.fxml", this);
        teacherController = (PersonController) data.getKey();
        List<String> teacherData = PersonController.getAdditionalDataOfTeacher(teacher);
        PersonController.setPersonData(teacher, pathToTeacherPhoto, teacherData, teacherController);
        return (VBox) data.getValue();
    }


    private VBox getStudentView(Student student) {
        Pair<Object, Pane> data = controllerFactory.getControllerAndPane("Person.fxml", this);
        studentController = (PersonController) data.getKey();
        List<String> studentData = PersonController.getAdditionalDataOfStudent(student);
        PersonController.setPersonData(student, pathToStudentPhoto, studentData, studentController);
        return (VBox) data.getValue();
    }


    private void updatePlayers() {
        PersonController.setPersonData(teacher, pathToTeacherPhoto,
                PersonController.getAdditionalDataOfTeacher(teacher), teacherController);
        PersonController.setPersonData(student, pathToStudentPhoto,
                PersonController.getAdditionalDataOfStudent(student), studentController);
    }


    private void updateQuestion() {
        studentChoices.getItems().clear();
        question = teacher.giveNextQuestion();
        sendMessage(question.getQuestion(), true);
        studentChoices.getItems().addAll(question.getChoices());
    }

    public void sendMessage(String message, boolean isTeacher){
        HBox hBox = new HBox();
        if(isTeacher){
            hBox.setAlignment(Pos.CENTER_LEFT);
        }else {
            hBox.setAlignment(Pos.CENTER_RIGHT);
        }
        hBox.getChildren().add(getMessageView(message));
        hBox.setMinHeight(messageController.getMinHeight());
        hBox.setMinWidth(335);
        VboxInScroll.getChildren().add(hBox);
        VboxInScroll.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, true));
        ScrollChatBox.vvalueProperty().bind(VboxInScroll.heightProperty());
    }

    @FXML
    void sendClicked(MouseEvent event) {
        ScrollChatBox.vvalueProperty().bind(VboxInScroll.heightProperty());
        if (null == currentAnswer) {
            return;
        }
        sendMessage(currentAnswer, false);
        missionManager.analiseResult(currentAnswer, question);
        sendMessage(teacher.say(), true);
        if (missionManager.getMission().missionAvailable()) {
            updateQuestion();
        } else {
            finishMission();
            return;
        }
        updatePlayers();
    }

    private void finishMission() {
        closeWindow();
    }

    private void closeWindow() {
        this.ScrollChatBox.getScene().getWindow().hide();
    }


}