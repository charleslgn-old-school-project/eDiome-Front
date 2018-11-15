package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resource.lang.Lang;

public class Main extends Application {

    private static Lang langue;

    private static Stage prStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/sample.fxml"));

        com.sun.javafx.util.Logging.getJavaFXLogger().setLevel(sun.util.logging.PlatformLogger.Level.OFF);

        setPrimaryStage(primaryStage);
        prStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../gui/NewUI.fxml"));
        primaryStage.setTitle("IRC");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return prStage;
    }

    public static void setPrimaryStage(Stage prStage) {
        Main.prStage = prStage;
    }

    public static Lang getLangue() {
        return langue;
    }

    public static void setLangue(Lang langue) {
        Main.langue = langue;
    }
}
