package com.ircserv.metier;


import java.io.Serializable;

public class PieceJointe implements Serializable {

    private int id_piece_jointe;
    private String chemin;
    private TypePieceJointe id_type_pj;


    public PieceJointe() { }

    public PieceJointe(String chemin, TypePieceJointe id_type_pj) {
        this.id_piece_jointe=-1;
        this.chemin=chemin;
        this.id_type_pj=id_type_pj;
    }

    public int getId_piece_jointe() {
        return id_piece_jointe;
    }

    public void setId_piece_jointe(int id_piece_jointe) {
        this.id_piece_jointe = id_piece_jointe;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public TypePieceJointe getId_type_pj() {
        return id_type_pj;
    }

    public void setId_type_pj(TypePieceJointe id_type_pj) {
        this.id_type_pj = id_type_pj;
    }
}
