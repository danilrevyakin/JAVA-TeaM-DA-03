package view.exam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;
import model.Person;
import model.Student;
import model.Teacher;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PersonController {
    @FXML
    private ImageView Avatar;

    @FXML
    private VBox Details;

    @FXML
    private VBox PersonView;
    private LinkedList<Text> detailsList = new LinkedList<>();

    public void setAvatar(String path) {
        Image image = new Image(getClass().getResourceAsStream(path));
        Avatar.setImage(image);
    }

    public void setDetails(List<String> details) {
        if(detailsList.size() != 0){
            Details.getChildren().removeAll(detailsList);
            detailsList.clear();
        }
        for(String data : details){
            Text text = new Text(data);
            detailsList.add(text);
            Details.getChildren().add(text);
        }
    }

    public static List<String> getAdditionalDataOfStudent(Student student){
        return List.of("Mana: " + student.getMana(),
                "Score: " + student.getScore(),
                "Level: " + student.getLevel());
    }

    public static List<String> getAdditionalDataOfTeacher(Teacher teacher){
        return List.of("Mode: " + teacher.getModeName(),
                "Number of questions: " + teacher.getQuestions().size());
    }


    static public void setPersonData(Person person, String pathToAvatar,
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

}
