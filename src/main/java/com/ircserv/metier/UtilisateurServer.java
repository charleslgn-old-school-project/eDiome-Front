package com.ircserv.metier;


import java.io.Serializable;

public class UtilisateurServer implements Serializable {
    private int id;
    private Utilisateur user;
    private Server server;

    public UtilisateurServer() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }


}
