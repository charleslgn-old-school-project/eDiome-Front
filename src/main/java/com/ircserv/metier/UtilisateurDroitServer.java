package com.ircserv.metier;

import java.io.Serializable;


public class UtilisateurDroitServer implements Serializable {

    private int id;
    private Droit droit;
    private Utilisateur user;
    private Server serveur;

    public UtilisateurDroitServer() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Droit getDroit() {
        return droit;
    }


    public void setDroit(Droit droit) {
        this.droit = droit;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Server getServeur() {
        return serveur;
    }

    public void setServeur(Server serveur) {
        this.serveur = serveur;
    }
}
