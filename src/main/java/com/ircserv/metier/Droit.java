package com.ircserv.metier;

import java.io.Serializable;


public class Droit implements Serializable {

    int id_droit;
    String libelle;
    String description;


    public Droit() {
    }

    public int getId_droit() {
        return id_droit;
    }

    public void setId_droit(int id_droit) {
        this.id_droit = id_droit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
