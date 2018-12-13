package com.ircfront.controller;

import com.ircfront.Utils.XMLDataFinder;
import com.jfoenix.controls.JFXButton;
import com.ircserv.inter.*;
import com.vdurmont.emoji.EmojiParser;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import com.ircserv.metier.*;
import com.ircfront.lang.Translate;
import com.ircfront.lang.typetrad.ButonName;
import com.ircfront.lang.typetrad.LabelName;
import com.ircfront.start.Main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class IRCController implements Initializable {

  private int nbMessages = 5;
  private String[] tabEmoji = {"\uD83D\uDE00", "\uD83D\uDE01", "\uD83D\uDE02", "\uD83E\uDD23", "\uD83D\uDE03"
  };

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
  private TextField textMessage,
          textPseudo;

  @FXML
  private BorderPane borderPane;

  @FXML
  private VBox VboxMere;

  private ServerInterface obj;

  private int nbServ;

  @SuppressWarnings("unused")
  private static Scanner sc;

  public IRCController(int nbServ) {
    this.nbServ = nbServ;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    textPseudo.setText(XMLDataFinder.getPseudo());

    textPseudo.setOnKeyReleased(e -> XMLDataFinder.setPseudo(textPseudo.getText()));

    paneChat.setVvalue(paneChat.getVmax());

    borderPane.setOnMouseClicked(e -> ScrollPaneEmoji.setVisible(false));

    textMessage.setOnKeyPressed(event -> {
      if (event.getCode().getName().equalsIgnoreCase("enter")) {
        send();
      }
    });

    /**new AnimationTimer() {
     private long lastUpdate = 0 ;
     @Override public void handle(long now) {
     if (now - (lastUpdate + 500_000_000) >= 999_000_000) {
     printChat();
     lastUpdate = now ;
     }
     translate();
     }
     }.start();*/

    sc = new Scanner(System.in);
    int port = Constante.PORT;
    try {
      String ip = Constante.IP;
      LocateRegistry.getRegistry(port);

      obj = (ServerInterface) Naming.lookup("//" + ip + ":" + port + "/serv" + nbServ);
      printChat(true);

    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    new AnimationTimer() {
      @Override
      public void handle(long now) {
        printChat(false);
        translate();
      }
    }.start();
    loadEmoji();
  }

  private void translate() {
    lblPseudo.setText(Translate.haveIt(LabelName.PSEUDO, Main.getLangue().label) + " : ");
    btnSend.setText(Translate.haveIt(ButonName.SEND, Main.getLangue().butonName));
  }

  private ArrayList<Message> oldMessage = null;
  private boolean sendByYou = false;


  private void makeSound() {
    try {
      MediaPlayer mp = new MediaPlayer(new Media(getClass().getResource("../../../resource/alert2.mp3").toString()));

      mp.play();
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  @FXML
  public void send() {
    try {
      if (!textMessage.getText().equalsIgnoreCase("")) {
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
  public void attachments() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Envoyer fichier");
    File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
    System.out.println(file);
  }

  @FXML
  public void displayEmoji() {
    if (ScrollPaneEmoji.isVisible()) {
      ScrollPaneEmoji.setVisible(false);
    } else {
      ScrollPaneEmoji.setVisible(true);
    }
  }

  public void loadEmoji() {
    //FlowPane fp = new FlowPane();
    //fp.setPrefSize(PaneEmoji.getPrefWidth(), PaneEmoji.getPrefHeight());
    //fp.getStyleClass().add("menu-bar-2");
    for (int i = 0; i <= tabEmoji.length - 1; i++) {
      Button jfxb = new Button("", getEmoji(tabEmoji[i]));

      jfxb.setUserData(tabEmoji[i]);
      jfxb.setPrefSize(50, 50);
      jfxb.setOnAction(event -> WriteEmoji(jfxb));
      PaneEmoji.getChildren().add(jfxb);
    }

    AnchorPane.setBottomAnchor(PaneEmoji, 0.0);
    //PaneEmoji.getChildren().add(fp);
  }

  private void WriteEmoji(Button emoji) {
    textMessage.appendText(EmojiParser.parseToAliases((String) emoji.getUserData()));
  }

  private ImageView getEmoji(String emmoji) {
    String emo = EmojiParser.parseToHtmlHexadecimal(emmoji);
    emo = emo.substring(3, emo.length() - 1);
    Image img = new Image(getClass().getResource("../../../emoji/" + emo + ".png").toString());
    ImageView img1 = new ImageView(img);
    img1.setFitWidth(16);
    img1.setFitHeight(16);
    return img1;
  }


  Message lastMessage;
  private void printChat(boolean initialize) {
    try {
      ArrayList<Message> messages;
      VBox vBox = (VBox) paneChat.contentProperty().getValue();
      if (initialize) {
        messages = obj.getMessages();

        for (Message message : messages) {
          vBox.getChildren().add(createMessage(message));
        }
        lastMessage = messages.get(messages.size()-1);
      } else {
        messages = obj.getMessages(1);
        if (!lastMessage.equals(messages.get(0))) {
          lastMessage=messages.get(0);
          vBox.getChildren().add(createMessage(messages.get(0)));
          if (oldMessage != null && oldMessage.size() < messages.size() && !sendByYou) {
            makeSound();
          }
          sendByYou = false;
        }
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private HBox createMessage(Message msg) {
    HBox hBox = new HBox();
    String affichage = msg.toString();
    affichage = EmojiParser.parseToAliases(affichage);
    String[] list = affichage.split(":");
    boolean wasLastAnEmoji = false;
    Label l = new Label(list[0]);
    hBox.getChildren().add(l);

    for (int i = 1; i < list.length; i++) {
      String str = EmojiParser.parseToHtmlHexadecimal(EmojiParser.parseToUnicode(':' + list[i] + ':'));
      if (str.equals(':' + list[i] + ':')) {
        Label label;
        if (list[i].equals("")) {
          label = new Label(':' + list[i]);
        } else if (!wasLastAnEmoji) {
          label = new Label(':' + list[i]);
        } else {
          label = new Label(list[i]);
        }
        hBox.getChildren().add(label);
        wasLastAnEmoji = false;
      } else {
        hBox.getChildren().add(getEmoji(str));
        wasLastAnEmoji = true;
      }
    }
    hBox.setAlignment(Pos.CENTER_LEFT);

    return hBox;
  }


}
