package view.map;

import controller.MissionManager;
import controller.hibernateUtil.PlayerDao;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Student;
import view.StageFactory;
import view.startWindow.RegistrationController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StaticMapController implements Initializable {

    private Student student;
    private MissionManager missionManager;

    public StaticMapController(Student student) {
        this.student = student;
    }

    public Label missionEnter;
    private int currentScene = 1;
    public Button bt1;
    private Stage stage;
    private Scene scene;
    private Parent root;


    //player move
    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    private BooleanProperty leftPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();
    private BooleanProperty f1Pressed = new SimpleBooleanProperty();

    public void noPress() {
        upPressed.set(false);
        downPressed.set(false);
        leftPressed.set(false);
        rightPressed.set(false);
        f1Pressed.set(false);
    }

    static public void playMapInGUI(Student student) {
        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth() * 3 / 4;
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight() * 3 / 4;
        Stage stage = new StageFactory((new StaticMapController(student)),
                "scene1.fxml",
                screenHeight, screenWidth, StaticMapController.class).getStage();
        stage.setResizable(false);
        stage.setTitle("Escape From KPI");
        stage.getIcons().add(new Image(Objects.requireNonNull(StaticMapController.class.getResourceAsStream("logo2.png"))));
        stage.show();
    }

    private BooleanBinding keyPressed = upPressed.or(leftPressed).or(downPressed).or(rightPressed);

    private int speed = 4;

    @FXML
    private Label infoLabel;
    @FXML
    private ImageView player;

    @FXML
    private AnchorPane scene1;
    @FXML
    private Label completed;

    @FXML
    public void start(ActionEvent e) {
        player.setLayoutY(100);
        player.setLayoutX(100);
    }


    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            //System.out.println(player.getLayoutX() + " " + player.getLayoutY());
            if (upPressed.get()) {
                player.setLayoutY(player.getLayoutY() - speed);
            }
            if (downPressed.get()) {
                player.setLayoutY(player.getLayoutY() + speed);
            }
            if (leftPressed.get()) {
                player.setLayoutX(player.getLayoutX() - speed);
            }
            if (rightPressed.get()) {
                player.setLayoutX(player.getLayoutX() + speed);
            }
            if (f1Pressed.get()) {
                System.out.println("Show info");
            }
            //
            changeScene();
            try {
                goToMission();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoLabel.setText("");
        movementSetup();
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    public void movementSetup() {
        scene1.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                upPressed.set(true);
            }
            if (e.getCode() == KeyCode.S) {
                downPressed.set(true);
            }
            if (e.getCode() == KeyCode.A) {
                leftPressed.set(true);
            }
            if (e.getCode() == KeyCode.D) {
                rightPressed.set(true);
            }
            if (e.getCode() == KeyCode.F1) {
                f1Pressed.set(true);
                infoLabel.setText("Score: " + student.getScore() +
                        "\nMana: " + student.getMana() +
                        "\nHealth: " + student.getHealth() +
                        "\nLevel: " + student.getLevel());
            }

        });

        scene1.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
                upPressed.set(false);
            }
            if (e.getCode() == KeyCode.S) {
                downPressed.set(false);
            }
            if (e.getCode() == KeyCode.A) {
                leftPressed.set(false);
            }
            if (e.getCode() == KeyCode.D) {
                rightPressed.set(false);
            }
            if (e.getCode() == KeyCode.F1) {
                f1Pressed.set(false);
                infoLabel.setText("");
            }
        });
    }


    //change img
    public void changeScene() {
        //System.out.println(player.getLayoutX() + " " + player.getLayoutY());

        String s = "-fx-background-image: url(\"view/map/map2.jpg\");";
        if (currentScene == 1) {
            if (player.getLayoutX() >= scene1.getWidth()) {
                scene1.setStyle(s);
                currentScene = 2;
                player.setLayoutX(10);
                return;
            }
            if (player.getLayoutY() >= scene1.getHeight()) {
                s = "-fx-background-image: url(\"view/map/map3.jpg\");";
                scene1.setStyle(s);
                player.setLayoutY(10);
                currentScene = 3;
                return;
            }
        }
        if (currentScene == 2) {
            if (player.getLayoutX() <= 0) {
                s = "-fx-background-image: url(\"view/map/map1.jpg\");";
                scene1.setStyle(s);

                currentScene = 1;
                player.setLayoutX(scene1.getWidth() - 10);
                return;
            }
            if (player.getLayoutY() >= scene1.getHeight()) {
                s = "-fx-background-image: url(\"view/map/map4.jpg\");";
                scene1.setStyle(s);

                currentScene = 4;
                player.setLayoutY(10);
                return;
            }

        }

        if (currentScene == 3) {
            if (player.getLayoutX() >= scene1.getWidth()) {
                s = "-fx-background-image: url(\"view/map/map4.jpg\");";
                scene1.setStyle(s);

                player.setLayoutX(10);
                currentScene = 4;
                return;
            }
            if (player.getLayoutY() <= 0) {
                s = "-fx-background-image: url(\"view/map/map1.jpg\");";
                scene1.setStyle(s);

                currentScene = 1;
                player.setLayoutY(scene1.getHeight() - 10);
                return;
            }
        }
        if (currentScene == 4) {
            if (player.getLayoutX() <= 0) {
                s = "-fx-background-image: url(\"view/map/map3.jpg\");";
                scene1.setStyle(s);
                currentScene = 3;
                player.setLayoutX(scene1.getWidth() - 10);
                return;
            }
            if (player.getLayoutY() <= 0) {
                scene1.setStyle(s);
                currentScene = 2;
                player.setLayoutY(scene1.getHeight() - 10);
            }
        }
    }

    public void goToMission() throws IOException, InterruptedException {
        int currentMission = 0;
        if (currentScene == 1) {
            if (player.getLayoutX() > 36 && player.getLayoutX() < 136) {
                if (player.getLayoutY() > 157 && player.getLayoutY() < 257) {
                    System.out.println("Enter 8 mission?");
                    currentMission = 8;
                }
            } else if (player.getLayoutX() > 384 && player.getLayoutX() < 484) {
                if (player.getLayoutY() > 341 && player.getLayoutY() < 441) {
                    System.out.println("Enter 4 mission?");
                    currentMission = 4;
                }
            }
        } else if (currentScene == 2) {
            if (player.getLayoutX() > 0 && player.getLayoutX() < 50) {
                if (player.getLayoutY() > 393 && player.getLayoutY() < 493) {
                    System.out.println("Enter 1 mission?");
                    currentMission = 1;

                }
            } else if (player.getLayoutX() > 524 && player.getLayoutX() < 624) {
                if (player.getLayoutY() > 349 && player.getLayoutY() < 449) {
                    System.out.println("Enter 5 mission?");
                    currentMission = 5;
                }
            } else if (player.getLayoutX() > 936 && player.getLayoutX() < 1036) {
                if (player.getLayoutY() > 243 && player.getLayoutY() < 343) {
                    System.out.println("Exit to game menu");
                    currentMission = -1;
                }
            }
        } else if (currentScene == 3) {
            if (player.getLayoutX() > 84 && player.getLayoutX() < 184) {
                if (player.getLayoutY() > 68 && player.getLayoutY() < 168) {
                    System.out.println("Enter 9 mission?");
                    currentMission = 9;
                }
            } else if (player.getLayoutX() > 680 && player.getLayoutX() < 780) {
                if (player.getLayoutY() > 66 && player.getLayoutY() < 166) {
                    System.out.println("Enter 7 mission?");
                    currentMission = 7;
                }
            } else if (player.getLayoutX() > 892 && player.getLayoutX() < 992) {
                if (player.getLayoutY() > 204 && player.getLayoutY() < 304) {
                    System.out.println("Enter 3 mission?");
                    currentMission = 3;

                }
            } else if (player.getLayoutX() > 712 && player.getLayoutX() < 812) {
                if (player.getLayoutY() > 360 && player.getLayoutY() < 460) {
                    System.out.println("Enter 2 mission?");
                    currentMission = 2;
                }
            }
        } else if (currentScene == 4) {
            if (player.getLayoutX() > 28 && player.getLayoutX() < 128) {
                if (player.getLayoutY() > 272 && player.getLayoutY() < 372) {
                    System.out.println("Enter 10 mission?");
                    currentMission = 10;
                }
            } else if (player.getLayoutX() > 220 && player.getLayoutX() < 320) {
                if (player.getLayoutY() > 120 && player.getLayoutY() < 220) {
                    System.out.println("Enter 6 mission?");
                    currentMission = 6;
                }
            }
        }
        if (currentMission != 0) {
            completed.setText("");
            timer.stop();
            noPress();
            player.setLayoutX(player.getLayoutX() + 50);
            player.setLayoutY(player.getLayoutY() + 50);
            //Check
            if (currentMission != -1) {
                if (student.availableMissions.get(currentMission).missionAvailable())
                    ConfirmBox.display("Enter " + currentMission + " mission?", student, currentMission);
                else {
                    System.out.println("Mission is completed!");
                    completed.setText("Mission " + currentMission + " completed");
                    // completed.setText("");
                }
            } else {
                //ConfirmBox.display("Exit to game menu?");
                PlayerDao playerDao = new PlayerDao();
                Stage st = (Stage) scene1.getScene().getWindow();
                st.close();
                playerDao.update(student.getPlayer());
                URL url = RegistrationController.class.getResource("Hello-view.fxml");
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
            }
            timer.start();
        }
    }
}


