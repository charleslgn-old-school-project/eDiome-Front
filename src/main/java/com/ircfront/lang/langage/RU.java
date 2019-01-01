package com.ircfront.lang.langage;

import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.lang.Lang;

public class RU extends Lang {

    @Override
    protected String[] getBoutonName() {
        String send       = "Послать";

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
        String pseudo  = "Псевдо";

        return new String[] {pseudo};
    }

    @Override
    protected String[] getMenu() {
        String menuMenu     = "Меню";
        String menuHelp     = "Помощь";
        String menuLangage  = "Язык";
        String menuAbout    = "О";
        String menuStyle    = "Стиль";
        return new String[] {menuMenu, menuHelp, menuLangage, menuAbout, menuStyle};
    }

    @Override
    protected String[] getDisclimer() {
        int[] date = XMLDataFinder.getBuildDate();
        String lblBuild   = "Сделано на "+  date[0] + " " + getMois()[date[1]-1] + " " + date[2];
        String lblRealise = "Режиссер :";
        String lblCadre   = "Для курса \"Управление проектом\"";
        String lblVersion   = "Версия";
        return new String[]{lblBuild, lblRealise, lblCadre, lblVersion};
    }

    @Override
    protected String[] getMois() {
        String january   = "январь";
        String february  = "февраль";
        String march     = "Марс";
        String april     = "апреля";
        String may       = "может";
        String june      = "июнь";
        String july      = "июль";
        String august    = "августейший";
        String september = "сентябрь";
        String october   = "октября";
        String november  = "ноябрь";
        String december  = "декабрь";

        return new String[] {january, february, march, april, may,
                june, july, august, september, october, november, december};
    }
}
