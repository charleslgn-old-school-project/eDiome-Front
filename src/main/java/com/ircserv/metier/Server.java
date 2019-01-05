package com.ircserv.metier;


import java.io.Serializable;



public class Server implements Serializable {
  private int id;
  private String name;
  private Utilisateur createur;

  public Server(int id, String name) {
    this.id = id;
    this.name = name;

  }

  public Server() {}

  public Server(String name) {
    this(-1, name);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }


  public Utilisateur getCreateur() {
    return createur;
  }

  public void setCreateur(Utilisateur createur) {
    this.createur = createur;
  }
}
