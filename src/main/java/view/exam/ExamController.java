package view.exam;

import controller.MissionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ExamController implements Initializable {
    @FXML
    private Pane BottomPane;

    @FXML
    private VBox CenterPane;

    @FXML
    private ChoiceBox<?> ChoiceReactBox;

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


    private final String pathToTeacherPhoto = "oldTeacher.png";
    private final String pathToStudentPhoto = "studentBrown.png";

    private QuestionController questionController;
    private Question question;

    private Student student;
    private PersonController studentController;
    private Teacher teacher;
    private PersonController teacherController;
    private final MissionManager missionManager;

    private SeparatorFactory factory = new SeparatorFactory();


    public ExamController(Student student, Teacher teacher, MissionManager missionManager) {
        this.missionManager = missionManager;
        System.out.println("constr");
        this.student = student;
        this.teacher = teacher;
    }

    static public void playMissionInGUI(Mission mission, MissionManager missionManager){
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
        stage.showAndWait();
    }

    private VBox getTeacherView(Teacher teacher) {
        Pair<Object, VBox> data = getControllerAndVbox("Person.fxml");
        teacherController = (PersonController)data.getKey();
        setPersonData(teacher, pathToTeacherPhoto, List.of("Mode: " + teacher.getModeName()), teacherController);
        return data.getValue();
    }


    private VBox getStudentView(Student student) {
        Pair<Object, VBox> data = getControllerAndVbox("Person.fxml");
        studentController = (PersonController)data.getKey();
        setPersonData(student, pathToStudentPhoto, null, studentController);
        return data.getValue();
    }

    private void updatePlayers(){
        setPersonData(teacher, pathToTeacherPhoto,
                List.of("Mode: " + teacher.getModeName()), teacherController);
        setPersonData(student, pathToStudentPhoto,
                null, studentController);
    }

    private void setPersonData(Person person, String pathToAvatar,
                          List<String> additionalDetails, PersonController controller){
        controller.setAvatar(pathToAvatar);
        List<String> list = new LinkedList<>();
        list.add(person.getName());
        list.add("Health: " + person.getHealth());
        if (additionalDetails != null) {
            list.addAll(additionalDetails);
        }
        controller.setDetails(list);
    }

    private Pair<Object, VBox> getControllerAndVbox(String pathToFXML){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pathToFXML));
        VBox vBox;
        try {
            vBox = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Object controller = fxmlLoader.getController();
        return new Pair<>(controller, vBox);
    }
    private VBox getQuestion(Question question){
        Pair<Object, VBox> data = getControllerAndVbox("Question.fxml");
        questionController = (QuestionController)data.getKey();
        questionController.setQuestion(question);
        return data.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeacherPane.getChildren().add(getTeacherView(teacher));
        StudentPane.getChildren().add(getStudentView(student));
        updateQuestion();
    }


    private void updateQuestion(){
        question = teacher.giveNextQuestion();
        VboxInScroll.getChildren().add(getQuestion(question));
    }

    @FXML
    void sendClicked(MouseEvent event) {
        String currentAnswer = questionController.getCurrentAnswer();
        ScrollChatBox.vvalueProperty().bind(VboxInScroll.heightProperty());
        if(null == currentAnswer){
            return;
        }
        printResult(missionManager.analiseResult(currentAnswer, question));
        questionController.setDisabledCurrentChoices();
        if(missionManager.getMission().missionAvailable()){
            updateQuestion();
        }else{
            finishMission();
            return;
        }
        ScrollChatBox.vvalueProperty().bind(VboxInScroll.heightProperty());
        updatePlayers();
    }

    private void finishMission(){
        closeWindow();
    }

    private void closeWindow(){
        this.ScrollChatBox.getScene().getWindow().hide();
    }

    private void printResult(MissionManager.AnswerResult result){
        if(result.equals(MissionManager.AnswerResult.CorrectAnswer)){
            printStudentWasCorrect();
            return;
        }
        printStudentWasWrong();
    }

    private void printStudentWasCorrect(){

    }

    private void printStudentWasWrong(){

    }



}