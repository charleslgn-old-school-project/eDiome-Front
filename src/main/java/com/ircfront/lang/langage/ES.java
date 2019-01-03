package com.ircfront.lang.langage;

import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.lang.Lang;

public class ES extends Lang {

  @Override
  protected String[] getBoutonName() {
    String send       = "Enviar";

    return new String[] {send};
  }

  @Override
  protected String[] getThemeName() {
    String black = "Negro";
    String white = "Blanco";
    String pink  = "Rosa";
    String green = "Verde";

    return new String[] {black, white,
            pink, green};
  }

  @Override
  protected String[] getLabel() {
    String pseudo  = "Apodo";
    String fichier = "Enviar un archivo";

    return new String[] {pseudo, fichier};
  }

  @Override
  protected String[] getMenu() {
    String menuMenu     = "Menú";
    String menuHelp     = "Ayuda";
    String menuLangage  = "Lengua";
    String menuAbout    = "A proposito";
    String menuStyle    = "Estilo";
    return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
  }

  @Override
  protected String[] getDisclimer() {
    int[] date = XMLDataFinder.getBuildDate();
    String lblBuild   = "Hazlo "+  date[0] + " " + getMois()[date[1]-1] + " " + date[2];
    String lblRealise = "Realizado por :";
    String lblCadre   = "Como parte del tema \"Gestión de proyecto\"";
    String lblVersion = "Versión";
    return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
  }

  @Override
  protected String[] getMois() {
    String january   = "Enero";
    String february  = "Febrero";
    String march     = "Marzo";
    String april     = "Abril";
    String may       = "Mayo";
    String june       = "Junio";
    String july      = "Julio";
    String august    = "Agosto";
    String september = "Septiembre";
    String october   = "Octubre";
    String november  = "Noviembre";
    String december  = "Diciembre";

    return new String[] {january, february, march, april, may,
            june, july, august, september, october, november, december};
  }
}
