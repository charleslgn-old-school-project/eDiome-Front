package test;

import Utils.XMLDataFinder;
import resource.lang.Lang;
import resource.lang.Translate;
import resource.lang.langage.FR;
import resource.lang.typetrad.ColorName;

import java.lang.reflect.Constructor;

public class TestXMLDataFinder {
    public static void main(String[] args){
        try {
            Class<?> clazz = Class.forName("resource.lang.langage.FR");
            Constructor<?> ctor = clazz.getConstructor();
            Object object = ctor.newInstance();
            Lang lang = (Lang) object;
            System.out.println(Translate.haveIt(ColorName.GREEN, lang.themeName));
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
