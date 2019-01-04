package com.ircfront.controller;

import com.ircfront.start.Main;
import com.ircfront.utils.ControllerUtils;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.chaineofresponsability.NodeFinder;
import com.ircfront.utils.chaineofresponsability.typemessage.AudioFile;
import com.ircfront.utils.chaineofresponsability.typemessage.DefaultFile;
import com.ircfront.utils.chaineofresponsability.typemessage.DefaultMessage;
import com.ircfront.utils.chaineofresponsability.typemessage.Picture;
import com.ircfront.utils.constante.ServerConstante;
import com.ircfront.utils.lang.Translate;
import com.ircfront.utils.lang.typetrad.ButonName;
import com.ircfront.utils.lang.typetrad.LabelName;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Message;
import com.jfoenix.controls.JFXButton;
import com.vdurmont.emoji.EmojiParser;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IRCController implements Initializable {

  private String[] tabEmoji = {"\uD83D\uDE00", "\uD83D\uDE01", "\uD83D\uDE02", "\uD83E\uDD23", "\uD83D\uDE03"};

  @FXML
  private ScrollPane ScrollPaneEmoji;

  @FXML
  private JFXButton btnSend,
          btnemoji;

  @FXML
  private FlowPane PaneEmoji;

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

  private ServerInterface obj;

  private int nbServ;

  public IRCController(int nbServ) {
    this.nbServ = nbServ;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    textPseudo.setText(XMLDataFinder.getPseudo());

    textPseudo.setDisable(true);

    paneChat.setVvalue(paneChat.getVmax());

    borderPane.setOnMouseClicked(e -> ScrollPaneEmoji.setVisible(false));

    textMessage.setOnKeyPressed(event -> {
      if (event.getCode().getName().equalsIgnoreCase("enter")) {
        send();
      }
    });

    VBox vBox = (VBox) paneChat.contentProperty().getValue();
    vBox.setSpacing(10);

    int port = ServerConstante.PORT;
    try {
      String ip = ServerConstante.IP;
      LocateRegistry.getRegistry(port);

      obj = (ServerInterface) Naming.lookup("//" + ip + ":" + port + "/serv" + nbServ);
      printChat(true);

    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      e.printStackTrace();
    }

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        printChat(false);
        translate();
      }
    }.start();

    ScrollPaneEmoji.getStyleClass().add("menu-emoji");
    loadEmoji();
  }

  private void translate() {
    lblPseudo.setText(Translate.haveIt(LabelName.PSEUDO, Main.getLangue().label) + " : ");
    btnSend.setText(Translate.haveIt(ButonName.SEND, Main.getLangue().butonName));
  }

  private ArrayList<Message> oldMessage = null;
  private boolean sendByYou = false;

  @FXML
  private void send() {
    try {
      if (!textMessage.getText().trim().equalsIgnoreCase("")) {
        obj.send(textPseudo.getText().trim(), textMessage.getText().trim());
        textMessage.setText("");
        sendByYou = true;
        paneChat.setVvalue(paneChat.getVmax());
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void attachments() {
    try {

      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle(Translate.haveIt(LabelName.FICHIER, Main.getLangue().label));
      File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
      if(file != null) {
        byte[] data = FileUtils.readFileToByteArray(file);
        obj.uploadFile(textPseudo.getText().trim(), data, file.getName());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void displayEmoji() {
    if (ScrollPaneEmoji.isVisible()) {
      ScrollPaneEmoji.setVisible(false);
    } else {
      ScrollPaneEmoji.setVisible(true);
    }
  }

  private void loadEmoji() {
    //FlowPane fp = new FlowPane();
    //fp.setPrefSize(PaneEmoji.getPrefWidth(), PaneEmoji.getPrefHeight());
    //fp.getStyleClass().add("menu-bar-2");
    for (int i = 0; i <= tabEmoji.length - 1; i++) {
      Button jfxb = new Button("", ControllerUtils.getEmoji(tabEmoji[i]));

      jfxb.setUserData(tabEmoji[i]);
      jfxb.setPrefSize(20, 20);
      jfxb.setStyle("-fx-background-color: transparent;");
      jfxb.setOnAction(event -> WriteEmoji(jfxb));
      PaneEmoji.getChildren().add(jfxb);
    }

    AnchorPane.setBottomAnchor(PaneEmoji, 0.0);
    //PaneEmoji.getChildren().add(fp);
  }

  /**
   * add the emoji alias in the message
   */
  private void WriteEmoji(Button emoji) {
    textMessage.appendText(EmojiParser.parseToAliases((String) emoji.getUserData()));
  }


  private Message lastMessage;
  private boolean wasLastSend = false;

  /**
   * if initialize then initialize chat else add the last message if someone send a new message
   */
  private void printChat(boolean initialize) {
    VBox vBox = (VBox) paneChat.contentProperty().getValue();
    if (initialize) {
      printAllChat(vBox);
    } else {
      printLastMessage(vBox);
    }
  }

  /**
   * initialize chat
   */
  private void printAllChat(VBox vBox) {
    ArrayList<Message> messages;
    try {
      messages = obj.getMessages();
      for (Message message : messages) {
        vBox.getChildren().add(createMessage(message));
      }
      lastMessage = messages.get(messages.size() - 1);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * add the last message if someone send a new message
   */
  private void printLastMessage(VBox vBox) {
    ArrayList<Message> messages;
    try {
      messages = obj.getMessages(1);
      if (messages.size() != 0 && !lastMessage.equals(messages.get(0))) {
        lastMessage = messages.get(0);
        vBox.getChildren().add(createMessage(messages.get(0)));
        if (oldMessage != null && oldMessage.size() < messages.size() && !sendByYou) {
          ControllerUtils.makeSound();
        }
        sendByYou = false;
        wasLastSend = true;
      } else if (wasLastSend) {
        paneChat.setVvalue(paneChat.getVmax());
        wasLastSend = false;
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param msg Le message envoyé
   * @return le message formater dans uns VBox
   */
  private HBox createMessage(Message msg) {
    NodeFinder nodeFinder = new DefaultMessage(
                            new Picture(
                            new DefaultFile(
                            new AudioFile(
                            new DefaultMessage(null)))));

    HBox hBoxtotal = new HBox();
    hBoxtotal.setSpacing(10);
    hBoxtotal.setAlignment(Pos.CENTER_LEFT);

    VBox vBox = new VBox();
    HBox hBoxData = new HBox();

    //message data(pseudo + date)
    Label label1 = new Label(msg.getPseudo() + " - " + msg.getStringDate());
    label1.setFont(new Font(13));
    hBoxData.getChildren().add(label1);
    vBox.getChildren().add(hBoxData);
    //content
    vBox.getChildren().add(nodeFinder.resolve(msg));

    //add the logo
    hBoxtotal.getChildren().add(ControllerUtils.getProfilePicture(msg.getPseudo()));
    //add all content
    hBoxtotal.getChildren().add(vBox);
    return hBoxtotal;
  }


}
