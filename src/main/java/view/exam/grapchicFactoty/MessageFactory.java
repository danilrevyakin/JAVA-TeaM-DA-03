package view.exam.grapchicFactoty;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import view.exam.MessageController;

public class MessageFactory {
    private MessageController messageController = null;
    private final String message;
    private final HBox messageContainer;
    private final Object objectReference;
    public MessageFactory(String message, boolean isTeacher, Object objectReference) {
        this.message = message;
        this.objectReference = objectReference;
        messageContainer = new HBox();
        if (isTeacher) {
            messageContainer.setAlignment(Pos.CENTER_LEFT);
        } else {
            messageContainer.setAlignment(Pos.CENTER_RIGHT);
        }
        messageContainer.getChildren().add(getMessageView());
        messageContainer.setMinHeight(messageController.getMinHeight());
        messageContainer.setMinWidth(335);
    }

    private HBox getMessageView() {
        GUIControllerFactory controllerFactory = new GUIControllerFactory("Message.fxml", objectReference);
        messageController = (MessageController) controllerFactory.getController();
        messageController.setText(message);
        return (HBox) controllerFactory.getPane();
    }

    public HBox getMessageContainer() {
        return messageContainer;
    }
}
