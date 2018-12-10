package controller;

import Utils.XMLDataFinder;
import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import inter.ServerInterface;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import metier.Message;
import resource.lang.Translate;
import resource.lang.typetrad.ButonName;
import resource.lang.typetrad.LabelName;
import start.Main;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class IRCController implements Initializable {

    @FXML
    private JFXButton btnSend;

    @FXML
    private Label lblPseudo;


    @FXML
    private ScrollPane paneChat;

    @FXML
    private TextField textMessage;

    @FXML
    private TextField textPseudo;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox VboxMere;

    ServerInterface obj;

    private int nbServ;

    @SuppressWarnings("unused")
    private static Scanner sc;

    public IRCController(int nbServ){
        this.nbServ = nbServ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sc = new Scanner(System.in);
        int port = 8000;

        String ip = "192.168.1.11";
               ip = "localhost";
        try {
            obj = (ServerInterface) Naming.lookup("rmi://"+ip+":" + port + "/serv"+nbServ);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        textPseudo.setText(XMLDataFinder.getPseudo());

        textPseudo.setOnKeyReleased(e -> XMLDataFinder.setPseudo(textPseudo.getText()) );

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
                translate();
            }
        }.start();
    }

    private void translate(){
        lblPseudo.setText(Translate.haveIt(LabelName.PSEUDO, Main.getLangue().label) + " : ");
        btnSend.setText(Translate.haveIt(ButonName.SEND, Main.getLangue().butonName));
    }

    private ArrayList<Message> oldMessage=null;
    private boolean sendByYou = false;

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

            if(oldMessage != null && oldMessage.size() < messages.size() && !sendByYou){
                makeSound();
            }
            oldMessage = messages;
            sendByYou = false;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void makeSound(){
        try{
            MediaPlayer mp = new MediaPlayer(new Media(Paths.get("src/resource/alert2.mp3").toUri().toString()));

            mp.play();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    public void send(){
        try {
            if(!textMessage.getText().equalsIgnoreCase("")) {
                obj.send(textPseudo.getText(), textMessage.getText());
                textMessage.setText("");
                paneChat.setVvalue(paneChat.getVmax());
                sendByYou = true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void attachments(){
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void displayEmoji(){
        try {


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
