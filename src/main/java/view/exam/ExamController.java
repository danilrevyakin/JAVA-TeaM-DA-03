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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import view.grapchicFactoty.GUIControllerFactory;
import view.grapchicFactoty.SeparatorFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
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
    private final String pathToVladicPhoto = "Vladic.png";

    private final String pathToStudentPhoto = "studentBrown.png";
    private final String pathToExitPhoto = "EscapeFromIASA.png";
    private final String pathToDanonPhoto = "Danon.jpeg";

    private Question question;
    private final Student student;
    private PersonController studentController;
    private final Teacher teacher;
    private PersonController teacherController;
    private final MissionManager missionManager;
    private Button exitButton;
    private Button manaButton;

    private final SeparatorFactory factory = new SeparatorFactory();

    static public void playMissionInGUI(Student student) {
        URL url = ExamController.class.getResource("Exam.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(controllerClass -> new ExamController(student));
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

    public ExamController(Student student) {
        this.student = student;
        this.teacher = student.getCurrentMission().getTeacher();
        missionManager = new MissionManager();
        teacher.setStudent(student);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initStudentPane();
        initTeacherPane();
        initChat();
        updateQuestion();
    }

    private void initTeacherPane(){
        exitButton = initExitButton();
        VBox box = new VBox();
        box.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
        box.getChildren().add(getTeacherView(teacher));
        box.getChildren().add(exitButton);
        TeacherPane.getChildren().add(box);
        TeacherPane.setAlignment(Pos.TOP_CENTER);
        box.setAlignment(Pos.TOP_CENTER);
    }

    private void initChat(){
        studentChoices.setOnAction(e->currentAnswer = studentChoices.getValue());
        VboxInScroll.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
    }
    private void initStudentPane(){
        VBox box = new VBox();
        manaButton = createManaButton();
        manaButton.setMinWidth(120);
        manaButton.setMinHeight(50);
        box.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
        box.getChildren().add(getStudentView(student));
        box.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
        box.getChildren().add(manaButton);
        StudentPane.getChildren().add(box);
        box.setAlignment(Pos.TOP_CENTER);
        StudentPane.setAlignment(Pos.TOP_CENTER);
    }



    public Button createManaButton() {
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
        button.setOnAction(e-> manaButtonAction());
        return button;
    }

    private void manaButtonAction(){
        Teacher.TransferManaStatus status = teacher.tryUseMana();
        sendMessage(teacher.say(), true);
        if(Teacher.TransferManaStatus.SUCCESSFUL_USED_MANA == status){
            updateQuestion();
            updatePlayers();
        }
    }

    public Button initExitButton() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathToExitPhoto)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        Button exitButton = new Button();
        exitButton.setGraphic(imageView);
        exitButton.setOnAction(e-> closeWindow());
        return exitButton;
    }

    private MessageController messageController;

    private HBox getMessageView(String stringMessage) {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Message.fxml", this);
        messageController = (MessageController) controllerFactory.getController();
        messageController.setText(stringMessage);
        return (HBox) controllerFactory.getPane();
    }


    private VBox getTeacherView(Teacher teacher) {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Person.fxml", this);
        teacherController = (PersonController) controllerFactory.getController();
        List<String> teacherData = PersonController.getAdditionalDataOfTeacher(teacher);
        PersonController.setPersonData(teacher, pathToVladicPhoto, teacherData, teacherController);
        return (VBox) controllerFactory.getPane();
    }


    private VBox getStudentView(Student student) {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Person.fxml", this);
        studentController = (PersonController) controllerFactory.getController();
        List<String> studentData = PersonController.getAdditionalDataOfStudent(student);
        PersonController.setPersonData(student, pathToDanonPhoto, studentData, studentController);
        return (VBox) controllerFactory.getPane();
    }


    private void updatePlayers() {
        PersonController.setPersonData(teacher, pathToVladicPhoto,
                PersonController.getAdditionalDataOfTeacher(teacher), teacherController);
        PersonController.setPersonData(student, pathToDanonPhoto,
                PersonController.getAdditionalDataOfStudent(student), studentController);
    }


    private void updateQuestion() {
        exitButton.setDisable(true);
        studentChoices.getItems().clear();
        question = teacher.giveNextQuestion();
        sendMessage(question.getQuestion(), true);
        studentChoices.getItems().addAll(question.getChoices());
    }

    public void sendMessage(String message, boolean isTeacher) {
        if (message.length() <= 4 && isTeacher) {
            return;
        }
        HBox hBox = new HBox();
        if (isTeacher) {
            hBox.setAlignment(Pos.CENTER_LEFT);
        } else {
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
        exitButton.setDisable(false);
        sendMessage(currentAnswer, false);
        missionManager.analiseResult(currentAnswer, question, student.getCurrentMission());
        sendMessage(teacher.say(), true);
        updatePlayers();
        if (student.getCurrentMission().missionAvailable()) {
            updateQuestion();
            return;
        }
        finishMission();
    }

    private void finishMission() {
        manaButton.setDisable(true);
        studentChoices.setDisable(true);
        SendButton.setDisable(true);
        sendMessage(student.getCurrentMission().getStateMission().getNormalName(), true);
    }

    private void closeWindow() {
        Stage stage = (Stage) this.BottomPane.getScene().getWindow();
        stage.close();
    }
}