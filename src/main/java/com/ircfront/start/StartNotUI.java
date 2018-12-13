package com.ircfront.start;

import com.ircserv.inter.*;
import com.ircserv.metier.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartNotUI {
  public static void main(String[] args){
    System.setProperty("java.rmi.server.hostname","home.rscharff.fr");
    int port = Constante.PORT;
    try {
      String ip = Constante.IP;
             //ip = "192.168.1.185";
      LocateRegistry.getRegistry(port);

      ServerInterface obj = (ServerInterface) Naming.lookup("rmi://"+ip+":" + port + "/serv0");

      System.out.println(obj.getMessages().size());
      obj.send("serveur", "test !");
    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
