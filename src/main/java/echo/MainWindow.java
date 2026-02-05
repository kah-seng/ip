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
        this.dialogContainer.getChildren().add(
                DialogBox.getEchoDialog(Ui.getWelcome(), this.echoImage)
        );
    }

    public void setEcho(Echo echo) {
        this.echo = echo;
    }

    @FXML
    private void handleUserInput() {
        String userInput = this.userInput.getText();
        String response = this.echo.parseAndExecute(userInput);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput, this.userImage),
                DialogBox.getEchoDialog(response, this.echoImage)
        );
        this.userInput.clear();

    }
}
