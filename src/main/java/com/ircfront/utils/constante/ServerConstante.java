package com.ircfront.utils.constante;

import com.ircserv.inter.MenuInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerConstante {
  public static final int PORT  = 186;
  public static final String IP = "localhost";
  public static MenuInterface MENU;
}
