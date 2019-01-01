package com.ircserv.inter;


import com.ircserv.metier.Server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MenuInterface extends Remote {
    int createNewServer() throws RemoteException;
    void deleteServer(int nbServ) throws RemoteException;
    ArrayList<Server> findServerByUser(int userId) throws RemoteException;

}
