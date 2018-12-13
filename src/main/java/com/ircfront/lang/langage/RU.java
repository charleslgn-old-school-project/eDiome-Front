package com.ircfront.lang.langage;

import com.ircfront.lang.Lang;

public class RU extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send       = "Send";

        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Black but more Russian";
        String white = "White but more Russian";
        String pink  = "Pink but more Russian";
        String green = "Green but more Russian";

        return new String[] {black, white,
                pink, green};
    }

    @Override
    protected String[] getLabel() {
        String pseudo  = "Написать на русском";

        return new String[] {pseudo};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Меню";
        String menuHelp     = "Помощь";
        String menuLangage  = "Язык";
        String menuAbout    = "О";
        String menuStyle    = "О";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        String lblBuild   = "Build n°40969d7 du 07 Novembre 2018";
        String lblRealise = "Réalisé par :";
        String lblCadre   = "Dans le cadre de la matière \"Algo Java\"";
        return new String[]{lblBuild, lblRealise, lblCadre};
    }
}
