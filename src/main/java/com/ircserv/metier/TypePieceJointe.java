package com.ircserv.metier;

import java.io.Serializable;


public class TypePieceJointe implements Serializable {

    private int id_type_pj;
    private String libelle;
    private String extension;


    public TypePieceJointe() {}

    public int getId_type_pj() {
        return id_type_pj;
    }

    public void setId_type_pj(int id_type_pj) {
        this.id_type_pj = id_type_pj;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
