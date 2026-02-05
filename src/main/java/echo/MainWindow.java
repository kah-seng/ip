package echo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Echo echo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image echoImage = new Image(this.getClass().getResourceAsStream("/images/Echo.png"));

    @FXML
    private void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setEcho(Echo echo) {
        this.echo = echo;
    }

    @FXML
    private void handleUserInput() {
        String userInput = this.userInput.getText();
        this.dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userInput, this.userImage)
        );
        this.userInput.clear();
        this.echo.parseAndExecute(userInput);
    }

    public void showMessage(String message) {
        this.dialogContainer.getChildren().add(
                DialogBox.getEchoDialog(message, this.echoImage)
        );
    }
}
