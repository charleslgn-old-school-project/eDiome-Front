package com.ircserv.metier;

import java.io.Serializable;

public class Server implements Serializable {
  private int id;
  private String name;

  public Server(int id, String name) {
    this.id = id;
    this.name = name;
  }

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
}
