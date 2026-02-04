package echo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {
    private VBox dialogContainer = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private TextField userInput = new TextField();
    private Button sendButton = new  Button("Send");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image echoImage = new Image(this.getClass().getResourceAsStream("/images/Echo.png"));
    private Echo echo = new Echo(Paths.get("data", "Echo.txt"));

    @Override
    public void start(Stage stage) {
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setContent(this.dialogContainer);

        this.userInput.setPrefWidth(325.0);
        this.userInput.setOnAction((event) -> {
            handleUserInput();
        });
        this.sendButton.setPrefWidth(55.0);
        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(mainLayout);
        stage.setTitle("Echo");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        String userText = this.userInput.getText();
        String echoText = this.echo.getResponse(userText);

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, this.userImage),
                DialogBox.getDukeDialog(echoText, this.echoImage));
        this.userInput.clear();
    }
}
