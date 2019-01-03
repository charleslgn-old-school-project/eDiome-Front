package com.ircfront.lang.langage;

import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.lang.Lang;

public class RU extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send       = "Отослать";

        return new String[] {send};
    }

    @Override
    protected String[] getThemeName() {
        String black = "Черный";
        String white = "белый";
        String pink  = "Розовый";
        String green = "Зеленый";

        return new String[] {black, white,
                pink, green};
    }

    @Override
    protected String[] getLabel() {
        String pseudo  = "Псевдоним";
        String fichier = "Отправить файл";

        return new String[] {pseudo, fichier};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Меню";
        String menuHelp     = "Помощь";
        String menuLangage  = "Язык";
        String menuAbout    = "О программе";
        String menuStyle    = "Стиль";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        int[] date = XMLDataFinder.getBuildDate();
        String lblBuild   = "сделано "+  date[0] + " " + getMois()[date[1]-1] + " " + date[2];
        String lblRealise = "Режиссер :";
        String lblCadre   = "Для курса \"Управление проектом\"";
        String lblVersion   = "Версия";
        return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
    }

    @Override
    protected String[] getMois() {
        String january   = "Январь";
        String february  = "Февраль";
        String march     = "Март";
        String april     = "Aпрель";
        String may       = "Май";
        String june      = "Июнь";
        String july      = "Июль";
        String august    = "Aвгуст";
        String september = "Сентябрь";
        String october   = "Октябрь";
        String november  = "Ноябрь";
        String december  = "Декабрь";

        return new String[] {january, february, march, april, may,
                june, july, august, september, october, november, december};
    }
}
