package com.ircfront.lang;

public abstract class Lang {

    protected abstract String[] getBoutonName();
    protected abstract String[] getThemeName();
    protected abstract String[] getLabel();
    protected abstract String[] getMenu();
    protected abstract String[] getDisclimer();

    public LambdaString butonName = (pos) -> getBoutonName()[pos];
    public LambdaString themeName = (pos) -> getThemeName()[pos];
    public LambdaString label     = (pos) -> getLabel()[pos];
    public LambdaString menu      = (pos) -> getMenu()[pos];
    public LambdaString disclimer = (pos) -> getDisclimer()[pos];
}
