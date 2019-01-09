package com.ircserv.metier;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Server server;
    private Timestamp date;
    private Utilisateur user;
    private String contenu;
    private PieceJointe pieceJointe;


    public Message(int id, Utilisateur user, Timestamp date, String contenu, Server server, PieceJointe pieceJointe) {
        this.id = id;
        this.user = user;
        this.pieceJointe = pieceJointe;
        this.date = date;
        this.contenu = contenu;
        this.server = server;
    }

    public Message(){
        this.id=-1;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public PieceJointe getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(PieceJointe pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", user=" + user + ", typeMessage='" + pieceJointe + '\'' + ", date=" + date + ", contenu='" + contenu + '\'' + ", server=" + server + '}';
    }

    public static class MessageBuilder {
        private int id;
        private Server server;
        private Timestamp date;
        private Utilisateur user;
        private String contenu;
        private PieceJointe id_pj;

        public MessageBuilder() {
            this.id = -1;
        }

        public MessageBuilder addNoUser(int id){
            this.id = id;
            return this;
        }

        public MessageBuilder addServ(Server server){
            this.server = server;
            return this;
        }

        public MessageBuilder addPieceJointe(PieceJointe pJ){
            this.id_pj = pJ;
            return this;
        }

        public MessageBuilder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public MessageBuilder setUser(Utilisateur user) {
            this.user = user;
            return this;
        }

        public MessageBuilder setContenu(String contenu) {
            this.contenu = contenu;
            return this;
        }

        public Message build(){
            Message message = new Message();

            message.id          = this.id;
            message.server      = this.server;
            message.pieceJointe = this.id_pj;
            message.date        = this.date;
            message.user        = this.user;
            message.contenu     = this.contenu;

            return message;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, server, date, user, contenu, pieceJointe);
    }
}
