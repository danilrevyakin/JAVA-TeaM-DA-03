package view.exam.grapchicFactory;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Person;
import model.Student;
import model.Teacher;
import view.exam.PersonController;

import java.util.List;

public class PersonViewFactory {

    private PersonController controller;
    private final SeparatorFactory factory = new SeparatorFactory();

    private final StackPane pane;
    private final Person person;
    private final String pathToPhoto;
    private final Button button;
    private final Object objectReference;
    public PersonViewFactory(StackPane pane, Person person, String pathToPhoto, Button button, Object objectReference) {
        this.pane = pane;
        this.person = person;
        this.pathToPhoto = pathToPhoto;
        this.button = button;
        this.objectReference = objectReference;
        personPane();
    }

    private void personPane() {
        VBox box = new VBox();
        VBox personBox = null;
        if (person instanceof Teacher) {
            personBox = getTeacherView((Teacher) person, pathToPhoto);
        } else if (person.getClass().equals(Student.class)) {
            personBox = getStudentView((Student) person, pathToPhoto);
        }
        box.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
        box.getChildren().add(personBox);
        box.getChildren().add(factory.getSeparator(Orientation.VERTICAL, 20, false));
        box.getChildren().add(button);
        pane.getChildren().add(box);
        box.setAlignment(Pos.TOP_CENTER);
        pane.setAlignment(Pos.TOP_CENTER);
    }

    private VBox getTeacherView(Teacher teacher, String pathToTeacherPhoto) {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Person.fxml", objectReference);
        controller = (PersonController) controllerFactory.getController();
        List<String> teacherData = PersonController.getAdditionalDataOfTeacher(teacher);
        PersonController.setPersonData(teacher, pathToTeacherPhoto, teacherData, controller);
        return (VBox) controllerFactory.getPane();
    }

    private VBox getStudentView(Student student, String pathToStudentPhoto) {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Person.fxml", objectReference);
        controller = (PersonController) controllerFactory.getController();
        List<String> studentData = PersonController.getAdditionalDataOfStudent(student);
        PersonController.setPersonData(student, pathToStudentPhoto, studentData, controller);
        return (VBox) controllerFactory.getPane();
    }

    public PersonController getController() {
        return controller;
    }
}
