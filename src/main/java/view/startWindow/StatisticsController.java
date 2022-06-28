package view.startWindow;

import controller.hibernateUtil.PlayerDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticsController {
    @FXML
    private Button goBackButton;

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
        setCellValueFactory();
        students = sortStudents();

        for (Player student : students) {
            table.getItems().add(student);
        }

        goBackButton.setOnAction(event -> WindowChange.openNewScene(
                "hello-view.fxml",
                table.getScene(),
                AuthorizationController.class.getName()));
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

    private void setCellValueFactory(){
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        manaColumn.setCellValueFactory(new PropertyValueFactory<>("mana"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));
    }
}
