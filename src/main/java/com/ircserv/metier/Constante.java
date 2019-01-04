package com.ircserv.metier;

import com.ircserv.inter.MenuInterface;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Constante implements Serializable {
  public static final int PORT  = 186;
  public static final String IP = "localhost";
  public static MenuInterface menu;

  static {
    try {
      LocateRegistry.getRegistry(Constante.PORT);
      menu = (MenuInterface) Naming.lookup("//" + Constante.IP + ":" + Constante.PORT + "/menu");
    } catch (NotBoundException | MalformedURLException | RemoteException e) {
      e.printStackTrace();
    }
  }

  ;
}
