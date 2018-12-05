package controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import inter.ServerInterface;
import javafx.stage.Stage;
import metier.Message;
import start.Main;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class IRCController implements Initializable {

    @FXML
    private ScrollPane paneChat;

    @FXML
    private TextField textMessage;

    @FXML
    private TextField textPseudo;

    @FXML
    private VBox VboxMere;

    ServerInterface obj;

    @SuppressWarnings("unused")
    private static Scanner sc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*(Main.getPrimaryStage()).widthProperty().addListener((obs, oldVal, newVal) -> {
            if(Main.getPrimaryStage().isMaximized()){
            VboxMere.setPrefSize(Main.getPrimaryStage().getWidth(),Main.getPrimaryStage().getHeight());
            }else {
                VboxMere.setPrefSize(Main.getPrimaryStage().getWidth(),Main.getPrimaryStage().getHeight());
            }
        });*/

        (Main.getPrimaryStage()).widthProperty().addListener((obs, oldVal, newVal) -> {
            if(Main.getPrimaryStage().isMaximized()){
                VboxMere.setPrefSize(Main.getPrimaryStage().getWidth(),Main.getPrimaryStage().getHeight());
            }else {
                VboxMere.setPrefSize(Main.getPrimaryStage().getWidth(),Main.getPrimaryStage().getHeight() * 0.7);
            }
        });


        sc = new Scanner(System.in);
        int port = 8000;
        try {
            obj = (ServerInterface) Naming.lookup("rmi://localhost:" + port + "/serv0");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        paneChat.setVvalue(paneChat.getVmax());

        textMessage.setOnKeyPressed(event -> {
            if(event.getCode().getName().equalsIgnoreCase("enter")){
                send();
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                printChat();
            }
        }.start();
    }

    private void printChat(){
        try {
            ArrayList<Message> messages = obj.getMessages();
            VBox vBox = new VBox();
            vBox.setSpacing(10);

            for (Message message: messages) {
                Label label = new Label(message.toString());
                vBox.getChildren().add(label);
            }
            paneChat.contentProperty().setValue(vBox);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void send(){
        try {
            if(!textMessage.getText().equalsIgnoreCase("")) {
                obj.send(textPseudo.getText(), textMessage.getText());
                textMessage.setText("");
                paneChat.setVvalue(paneChat.getVmax());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
