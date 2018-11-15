package resource.lang.langage;

import resource.lang.Lang;

public class FR extends Lang {
    @Override
    protected String[] getBoutonName() {
        String chooseFile       = "Choisir un fichier";
        String translate        = "Traduire";
        String playTranslate    = "Jouer la traduction";
        String export           = "Exporter";
        String newTranslate     = "Nouvelle traduction";
        String restart          = "Recommencer";

        return new String[] {chooseFile, translate, playTranslate,
                export, newTranslate, restart};
    }

    @Override
    protected String[] getTitleName() {
        String languageToMorse  = "Serveur 1";
        String languageToL33t   = "Serveur 2";
        String morseToLanguage  = "etc";
        String directTranslate  = "...";

        return new String[] {languageToMorse, languageToL33t,
                morseToLanguage, directTranslate};
    }

    @Override
    protected String[] getLabel() {
        String writeRoman  = "";
        String writeMorse  = "";
        String welcome     = "Bienvenue";
        String title       = "eDiome";

        return new String[] {writeRoman, writeMorse, welcome, title};
    }

    @Override
    protected String[] getPopUp() {
        String chooseFileTrad  = "Choisissez un fichier texte";
        String chooseFileExport= "Choisissez un répertoire ou exporter votre traduction";

        String popUpSuccesTittle    = "Succès";
        String popUpSuccesName      = "Export";
        String popUpSuccesDesc      = "La traduction a été exportée";

        String popUpErrorTittle     = "Erreur";
        String popUpErrorName       = "Chemin du fichier";
        String popUpErrorDescTrad   = "Sélectionnez dans un premier temps un fichier texte à traduire";
        String popUpErrorDescExport = "Vérifiez qu'une traduction a été effectuée";

        String popUpErrorNoFileSelected = "Vérifiez qu'un fichier a été sélectionné";
        String popUpErrorPlayTrad       = "Vérifiez que la traduction a un résultat";

        return new String[] {chooseFileTrad, chooseFileExport, popUpSuccesTittle, popUpSuccesName,
                             popUpSuccesDesc, popUpErrorTittle, popUpErrorName, popUpErrorDescTrad, popUpErrorDescExport,
                            popUpErrorNoFileSelected, popUpErrorPlayTrad};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Menu";
        String menuHelp     = "Aide";
        String menuLangage  = "Langue";
        String menuAbout    = "A propos";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout};
    }

    @Override
    protected String[] getDisclimer() {
        String lblBuild   = "Build n°40969d7 du 07 Novembre 2018";
        String lblRealise = "Réalisé par :";
        String lblCadre   = "Dans le cadre de la matière \"Algo Java\"";
        return new String[]{lblBuild, lblRealise, lblCadre};
    }


}
