package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ThemeSaver {

  public static void saveTheme(String data){
    try {
      new File("./src/resource/css.txt").delete();
      PrintWriter writer = new PrintWriter("./src/resource/css.txt", "UTF-8");
      writer.println(data);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

}
