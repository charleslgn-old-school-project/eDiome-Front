package resource.lang.langage;

import resource.lang.Lang;

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
        String pseudo  = "pseudo";

        return new String[] {pseudo};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Menu";
        String menuHelp     = "Aide";
        String menuLangage  = "Langue";
        String menuAbout    = "A propos";
        String menuStyle    = "Theme";
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
