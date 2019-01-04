package com.ircfront.utils.lang.langage;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.lang.Lang;

public class EN extends Lang {

  @Override
  protected String[] getBoutonName() {
    String send = "Send";

    return new String[]{send};
  }

  @Override
  protected String[] getThemeName() {
    String black = "Black";
    String white = "White";
    String pink = "Pink";
    String green = "Green";

    return new String[]{black, white,
            pink, green};
  }

  @Override
  protected String[] getLabel() {
    String pseudo = "pseudo";
    String fichier = "Send a file";

    return new String[] {pseudo, fichier};
  }

  @Override
  protected String[] getMenu() {
    String menuMenu = "Menu";
    String menuHelp = "Help";
    String menuLangage = "Languages";
    String menuAbout = "About";
    String menuStyle = "Style";
    return new String[]{menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
  }

  @Override
  protected String[] getDisclimer() {
    int[] date = XMLDataFinder.getBuildDate();
    int nb = date[0]%10;
    String endNumber = nb == 1 ? "th" : nb == 2 ? "nd" : nb == 3 ? "rd" : "th";
    String lblBuild = "built on "+ getMois()[date[1]-1] +" "+ date[0] + endNumber + " " +date[2];
    String lblRealise = "Made by:";
    String lblCadre = "for the cours \"Project management\"";
    String lblVersion = "Version";
    return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
  }

  @Override
  protected String[] getMois() {
    String january = "January";
    String february = "February";
    String march = "March";
    String april = "April";
    String may = "May";
    String june = "June";
    String july = "July";
    String august = "August";
    String september = "September";
    String october = "October";
    String november = "November";
    String december = "December";

    return new String[]{january, february, march, april, may,
            june, july, august, september, october, november, december};
  }
}
