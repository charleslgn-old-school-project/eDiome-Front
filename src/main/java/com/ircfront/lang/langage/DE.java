package com.ircfront.lang.langage;

import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.lang.Lang;

public class DE extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send = "Senden";
        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Schwarz";
        String white = "Weiß";
        String pink  = "Rosa";
        String green = "Grün";

        return new String[] {black, white,
                pink, green};
    }

    @Override
    protected String[] getLabel() {
        String pseudo  = "Pseudo";
        String fichier = "Datei senden";

        return new String[] {pseudo, fichier};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Menü";
        String menuHelp     = "Hilfe";
        String menuLangage  = "Sprach";
        String menuAbout    = "Über";
        String menuStyle    = "Thema";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        int[] date = XMLDataFinder.getBuildDate();
        String lblBuild   = "von " + date[0] + ". " + getMois()[date[1]-1] + " " + date[2];
        String lblRealise = "Macht bei :";
        String lblCadre   = "Für \"Projektmanagement\" Untericht";
        String lblVersion = "Version";
        return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
    }

    @Override
    protected String[] getMois() {
        return new String[] {"Januar", "Februar", "März", "April", "May",
                "Juni", "Juli", "August", "September", "Oktober", "November", "December"};
    }


}
