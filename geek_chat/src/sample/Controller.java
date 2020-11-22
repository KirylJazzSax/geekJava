package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Connection connection;
    @FXML
    public ListView<String> messagesList;
    @FXML
    public TextField clientMessage;

    public void sendMessage() {
        if (!this.connection.established()) {
            return;
        }

        try {
            String msg = this.clientMessage.getText();
            this.connection.getOutStream().writeUTF(msg);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void connect() {
        if (this.connection != null && this.connection.established()) {
            return;
        }

        this.connection = new Connection("localhost", 8189);
        new Thread(() -> {
            while (true) {
                try {
                    String str = this.connection.getInStream().readUTF();
                    Platform.runLater(() -> {
                        this.messagesList.getItems().add(str);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.connect();
    }
}
