package com.ircserv.inter;


import com.ircserv.metier.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
    ArrayList<Message> getMessages() throws RemoteException;
    void send(String pseudo, String message) throws RemoteException;

}
