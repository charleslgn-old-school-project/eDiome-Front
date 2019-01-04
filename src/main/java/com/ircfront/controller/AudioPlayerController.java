package com.ircfront.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {

  @FXML
  private ToggleButton playPause;
  @FXML
  private ProgressBar mediaTime;
  @FXML
  private ImageView igmPlay;

  private MediaPlayer media;


  private String sound;

  public AudioPlayerController(String url) {
    sound = url;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    media = new MediaPlayer(new Media(Paths.get(sound).toUri().toString()));
    media.setAudioSpectrumListener((timestamp, duration, magnitudes, phases) -> {
      double all = media.getCycleDuration().toMillis();
      double current = media.getCurrentTime().toMillis();
      mediaTime.setProgress(current / all);
    });

    media.setOnEndOfMedia(() -> {
      playPause.setSelected(false);
      playPauseClicked();
      stopClicked();
    });
  }

  @FXML
  private void playPauseClicked() {
    if (playPause.isSelected()) {
      media.play();
      igmPlay.setImage(new Image("image/icon/pause.png"));
    } else {
      media.pause();
      igmPlay.setImage(new Image("image/icon/play.png"));
    }
  }

  @FXML
  private void stopClicked() {
    media.stop();
    mediaTime.setProgress(0);
  }

}
