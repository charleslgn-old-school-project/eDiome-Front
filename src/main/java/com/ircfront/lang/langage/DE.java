package com.ircfront.lang.langage;

import com.ircfront.lang.Lang;

public class DE extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send = "Send";
        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Schwarz";
        String white = "WeiBe";
        String pink  = "Rosa";
        String green = "Grün";

        return new String[] {black, white,
                pink, green};
    }

    @Override
    protected String[] getLabel() {
        String pseudo  = "pseudo";

        return new String[] {pseudo};
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
        String lblBuild   = "Build 40969d7 von 07. November 2018";
        String lblRealise = "Macht bei :";
        String lblCadre   = "Für \"Algo Java\" Untericht";
        return new String[]{lblBuild, lblRealise, lblCadre};
    }
}
