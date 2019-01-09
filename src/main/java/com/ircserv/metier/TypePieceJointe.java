package com.ircserv.metier;

import java.io.Serializable;
import java.util.Objects;


public class TypePieceJointe implements Serializable {

    private int id;
    private String libelle;
    private String extension;


    public TypePieceJointe() {
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypePieceJointe that = (TypePieceJointe) o;
        return id == that.id &&
                Objects.equals(libelle, that.libelle) &&
                Objects.equals(extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, extension);
    }
}
