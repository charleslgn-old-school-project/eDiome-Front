package com.ircserv.metier;


import java.io.Serializable;
import java.util.Objects;

public class PieceJointe implements Serializable {

    private int id;
    private String chemin;
    private TypePieceJointe typePieceJointe;


    public PieceJointe() {
        this.id =-1;
    }

    public PieceJointe(String chemin, TypePieceJointe typePieceJointe) {
        this.id =-1;
        this.chemin=chemin;
        this.typePieceJointe =typePieceJointe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public TypePieceJointe getTypePieceJointe() {
        return typePieceJointe;
    }

    public void setTypePieceJointe(TypePieceJointe typePieceJointe) {
        this.typePieceJointe = typePieceJointe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceJointe that = (PieceJointe) o;
        return id == that.id &&
                Objects.equals(chemin, that.chemin) &&
                Objects.equals(typePieceJointe, that.typePieceJointe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chemin, typePieceJointe);
    }
}
