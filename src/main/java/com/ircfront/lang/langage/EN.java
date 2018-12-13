package com.ircfront.lang.langage;

import com.ircfront.lang.Lang;

public class EN extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send       = "Send";

        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Black";
        String white = "White";
        String pink  = "Pink";
        String green = "Green";

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
        String menuMenu     = "Menu";
        String menuHelp     = "Help";
        String menuLangage  = "Languages";
        String menuAbout    = "About";
        String menuStyle    = "Style";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        String lblBuild   = "Build 40969d7 built on November 07 2018";
        String lblRealise = "Made by:";
        String lblCadre   = "for the cours \"Algo Java\"";
        return new String[]{lblBuild, lblRealise, lblCadre};
    }
}
