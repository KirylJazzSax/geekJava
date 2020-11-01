package sample.chatList;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ChatList extends VBox {
    public ChatList() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.setSpacing(5);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
