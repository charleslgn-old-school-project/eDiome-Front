package com.ircserv.metier;


import java.io.Serializable;


public class Utilisateur_Server implements Serializable {
    private int id;
    private Utilisateur no_utilisateur;
    private Server code_serveur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public Server getCode_serveur() {
        return code_serveur;
    }

    public void setCode_serveur(Server code_serveur) {
        this.code_serveur = code_serveur;
    }


}
