package resource.lang;

public abstract class Lang {

    protected abstract String[] getBoutonName();
    protected abstract String[] getTitleName();
    protected abstract String[] getLabel();
    protected abstract String[] getPopUp();
    protected abstract String[] getMenu();
    protected abstract String[] getDisclimer();

    public LambdaString butonName = (pos) -> getBoutonName()[pos];

    public LambdaString titleName = (pos) -> getTitleName()[pos];

    public LambdaString label     = (pos) -> getLabel()[pos];

    public LambdaString popUp     = (pos) -> getPopUp()[pos];

    public LambdaString menu      = (pos) -> getMenu()[pos];

    public LambdaString disclimer = (pos) -> getDisclimer()[pos];
}
