package start;

import Utils.ResizeHelper;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/IRC.fxml"));

        //com.sun.javafx.util.Logging.getJavaFXLogger().setLevel(sun.util.logging.PlatformLogger.Level.OFF);

        prStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../gui/NewUI2.fxml"));
        primaryStage.setTitle("IRC");
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        prStage.getIcons().add(new Image("/resource/Images/ediome2.png"));

        primaryStage.setScene(new Scene(root, 1280, 720));
        Main.getPrimaryStage().getScene().getStylesheets().add(getClass().getResource("..//gui/css/main-pink.css").toExternalForm());
        Font.loadFont("..//resource/emojione.ttf", 10);

        primaryStage.setResizable(true);

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        primaryStage.show();

        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("win")) {
            long lhwnd = com.sun.glass.ui.Window.getWindows().get(0).getNativeWindow();
            Pointer lpVoid = new Pointer(lhwnd);
            WinDef.HWND hwnd = new WinDef.HWND(lpVoid);
            final User32 user32 = User32.INSTANCE;

            int oldStyle = user32.GetWindowLong(hwnd, WinUser.GWL_STYLE);
            System.out.println(Integer.toBinaryString(oldStyle));
            int newStyle = oldStyle | 0x00020000;//WS_MINIMIZEBOX
            System.out.println(Integer.toBinaryString(newStyle));
            user32.SetWindowLong(hwnd, WinUser.GWL_STYLE, newStyle);
        }

        ResizeHelper.addResizeListener(getPrimaryStage());
    }

    public static Stage getPrimaryStage() {
        return prStage;
    }

    public static Lang getLangue() {
        return langue;
    }

    public static void setLangue(Lang langue) {
        Main.langue = langue;
    }
}
