package com.ircfront.lang.langage;

import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.lang.Lang;

public class FR extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send       = "Envoyer";

        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Noir";
        String white = "Blanc";
        String pink  = "Rose";
        String green = "Vert";

        return new String[] {black, white,
                pink, green};
    }

    @Override
    protected String[] getLabel() {
        String pseudo  = "Pseudo";

        return new String[] {pseudo};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Menu";
        String menuHelp     = "Aide";
        String menuLangage  = "Langue";
        String menuAbout    = "À propos";
        String menuStyle    = "Theme";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        int[] date = XMLDataFinder.getBuildDate();
        String lblBuild   = "fait le "+  date[0] + " " + getMois()[date[1]-1] + " " + date[2];
        String lblRealise = "Réalisé par :";
        String lblCadre   = "Dans le cadre de la matière \"Gestion de projet\"";
        String lblVersion = "Version";
        return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
    }

    @Override
    protected String[] getMois() {
        String january   = "Janvier";
        String february  = "Février";
        String march     = "Mars";
        String april     = "Avril";
        String may       = "Mai";
        String june       = "Juin";
        String july      = "Juillet";
        String august    = "Août";
        String september = "Septembre";
        String october   = "Octobre";
        String november  = "Novembre";
        String december  = "Décembre";

        return new String[] {january, february, march, april, may,
                june, july, august, september, october, november, december};
    }


}
