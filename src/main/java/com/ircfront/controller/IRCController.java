package com.ircfront.controller;

import com.ircfront.Utils.IRCUtils;
import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.Utils.chaineofresponsability.*;
import com.ircfront.lang.Translate;
import com.ircfront.lang.typetrad.ButonName;
import com.ircfront.lang.typetrad.LabelName;
import com.ircfront.start.Main;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Constante;
import com.ircserv.metier.Message;
import com.jfoenix.controls.JFXButton;
import com.vdurmont.emoji.EmojiParser;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
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
import java.util.Scanner;

public class IRCController implements Initializable {

  private int nbMessages = 25;
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
  private TextField textMessage;
  @FXML
  private TextField textPseudo;

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
      Button jfxb = new Button("", IRCUtils.getEmoji(tabEmoji[i]));

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
          IRCUtils.makeSound();
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
    NodeFinder nodeFinder = new HyperText(
            new Smilley(
                    new Picture(
                            new AudioFile(
                                    new DefaultFile(
                                            new Word(null))))));
    HBox hBoxtotal = new HBox();
    hBoxtotal.setSpacing(10);
    hBoxtotal.setAlignment(Pos.CENTER_LEFT);

    //add the logo
    try {
      String firstLetter = ("" + msg.getPseudo().charAt(0)).toUpperCase();
      ImageView img = new ImageView();

      Image image = new Image("logo/" + firstLetter + ".png");

      BufferedImage icon = SwingFXUtils.fromFXImage(image, null);
      BufferedImage rounded = IRCUtils.makeRoundedCorner(icon, 500);
      image = SwingFXUtils.toFXImage(rounded, null);

      img.setImage(image);
      img.getStyleClass().add("profile-logo");
      img.setFitHeight(40);
      img.setFitWidth(40);
      hBoxtotal.getChildren().add(img);
    } catch (Exception e) {
      e.printStackTrace();
    }

    VBox vBox = new VBox();
    HBox hBoxData = new HBox();
    FlowPane hBoxMessage = new FlowPane();

    //identifiant de message (pseudo + date)
    Label label1 = new Label(msg.getPseudo() + " - " + msg.getStringDate());
    label1.setFont(new Font(13));
    hBoxData.getChildren().add(label1);
    vBox.getChildren().add(hBoxData);
    //le message
    String affichage = msg.getContenu();
    affichage = EmojiParser.parseToAliases(affichage);
    String[] list = affichage.split(" ");


    for (String s : list) {
      Node node = nodeFinder.resolve(s);
      hBoxMessage.getChildren().add(node);
    }
    hBoxMessage.setHgap(2);
    hBoxMessage.setPadding(new Insets(0, 30, 0, 30));
    hBoxMessage.setAlignment(Pos.CENTER_LEFT);
    vBox.getChildren().add(hBoxMessage);

    //add all content
    hBoxtotal.getChildren().add(vBox);
    return hBoxtotal;
  }


}
