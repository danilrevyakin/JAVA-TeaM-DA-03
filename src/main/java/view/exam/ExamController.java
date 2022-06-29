package view.exam;

import controller.MissionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Question;
import model.Student;
import model.Teacher;
import view.StageFactory;
import view.exam.grapchicFactory.ButtonsDirector;
import view.exam.grapchicFactory.MessageFactory;
import view.exam.grapchicFactory.PersonViewFactory;
import view.exam.grapchicFactory.SeparatorFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamController implements Initializable {
    @FXML
    private Pane BottomPane;

    @FXML
    private ChoiceBox<String> studentChoices;

    @FXML
    private ScrollPane ScrollChatBox;

    @FXML
    private Button SendButton;

    @FXML
    private StackPane StudentPane;

    @FXML
    private StackPane TeacherPane;


    @FXML
    private VBox VboxInScroll;
    private String currentAnswer;

    private final String pathToVladicPhoto = "Vladic.png";

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
        Stage stage = (new StageFactory((new ExamController(student)),
                "Exam.fxml", ExamController.class)).getStage();
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

    private void initTeacherPane() {
        exitButton = ButtonsDirector.createExitButton(e -> closeWindow());
        teacherController = (new PersonViewFactory(TeacherPane, teacher,
                pathToVladicPhoto, exitButton, this)).getController();
    }

    private void initStudentPane() {
        manaButton = ButtonsDirector.createManaButton(e -> manaButtonAction());
        studentController = (new PersonViewFactory(StudentPane, student,
                pathToDanonPhoto, manaButton, this)).getController();
    }

    private void initChat() {
        studentChoices.setOnAction(e -> currentAnswer = studentChoices.getValue());
        VboxInScroll.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
    }

    private void manaButtonAction() {
        Teacher.TransferManaStatus status = teacher.tryUseMana();
        sendMessage(teacher.say(), true);
        if (Teacher.TransferManaStatus.SUCCESSFUL_USED_MANA == status) {
            updateQuestion();
            updatePlayers();
        }
    }

    private void updatePlayers() {
        PersonController.setPersonData(teacher, pathToVladicPhoto,
                PersonController.getAdditionalDataOfTeacher(teacher), teacherController);
        PersonController.setPersonData(student, pathToDanonPhoto,
                PersonController.getAdditionalDataOfStudent(student), studentController);
    }

    private void updateQuestion() {
        studentChoices.getItems().clear();
        question = teacher.giveNextQuestion();
        sendMessage(question.getQuestion(), true);
        studentChoices.getItems().addAll(question.getChoices());
    }

    public void sendMessage(String message, boolean isTeacher) {
        if (message.length() <= 4 && isTeacher) {
            return;
        }
        HBox messageContainer = (new MessageFactory(message, isTeacher, this)).getMessageContainer();
        VboxInScroll.getChildren().add(messageContainer);
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
        exitButton.setDisable(false);
        studentChoices.setDisable(true);
        SendButton.setDisable(true);
        sendMessage(student.getCurrentMission().getStateMission().getNormalName(), true);
    }

    private void closeWindow() {
        Stage stage = (Stage) this.BottomPane.getScene().getWindow();
        stage.close();
    }
}