package view.exam;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
}
