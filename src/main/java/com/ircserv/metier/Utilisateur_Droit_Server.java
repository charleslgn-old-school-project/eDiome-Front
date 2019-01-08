package com.ircserv.metier;

import java.io.Serializable;


public class Utilisateur_Droit_Server  implements Serializable {

    int id;
    Droit id_droit;
    Utilisateur no_utilisateur;
    Server code_serveur;

    public Utilisateur_Droit_Server() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Droit getId_droit() {
        return id_droit;
    }


    public void setId_droit(Droit id_droit) {
        this.id_droit = id_droit;
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
