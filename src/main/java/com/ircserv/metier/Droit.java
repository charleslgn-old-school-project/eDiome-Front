package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


public class Droit implements Serializable {

    private int id;
    private String libelle;
    private String description;


    public Droit() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Droit droit = (Droit) o;
        return id == droit.id &&
                Objects.equals(libelle, droit.libelle) &&
                Objects.equals(description, droit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, description);
    }
}
