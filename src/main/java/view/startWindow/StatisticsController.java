package view.startWindow;

import controller.hibernateUtil.PlayerDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticsController {
    @FXML
    private VBox CenterPane;

    @FXML
    private HBox ReactBox;

    @FXML
    private ScrollPane ScrollStatBox;

    @FXML
    private StackPane StudentPane;

    @FXML
    private StackPane TeacherPane;

    @FXML
    private Pane TopPane;

    @FXML
    private VBox VboxInScroll;

    @FXML
    private Button goBackButton;

    @FXML
    private Label nameLabel;

    @FXML
    private TableView<Player> table = new TableView<>();

    @FXML
    private TableColumn<Player, Integer> healthColumn;

    @FXML
    private TableColumn<Player, Integer> levelColumn;

    @FXML
    private TableColumn<Player, String> loginColumn;

    @FXML
    private TableColumn<Player, Integer> manaColumn;

    @FXML
    private TableColumn<Player, Integer> scoreColumn;

    private List<Player> students;

    public StatisticsController() {
        this.students = new ArrayList<>();
        PlayerDao playerDao = new PlayerDao();
        students = playerDao.findAll();
    }

    @FXML
    void initialize(){
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        manaColumn.setCellValueFactory(new PropertyValueFactory<>("mana"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));
        students = sortStudents();
        for (Player student : students) {
            table.getItems().add(student);
        }

        goBackButton.setOnAction(event -> {
            table.getScene().getWindow().hide();
            URL url = AuthorizationController.class.getResource("hello-view.fxml");
            FXMLLoader loader = new FXMLLoader(url);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    public ArrayList<Player> sortStudents(){
         List<Player> list = students.stream()
                 .sorted(Comparator.comparingInt(Player::getLevel)
                         .thenComparingInt(Player::getScore)
                         .thenComparingInt(Player::getMana)
                         .thenComparingInt(Player::getHealth)
                         .reversed()).toList();
         return new ArrayList<>(list);
    }
}
