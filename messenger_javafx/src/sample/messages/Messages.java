package sample.messages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Messages extends GridPane {
    @FXML
    public VBox messages;
    @FXML
    public TextField messageField;

    public Messages() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("messages.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void textFieldEvent(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.appendMessageToMessages();
        }
    }

    public void sendMessage(ActionEvent event) {
        this.appendMessageToMessages();
    }

    private void appendMessageToMessages() {
        this.messages.getChildren().add(
                new Label(this.messageField.getText())
        );
        this.messageField.setText("");
    }
}
