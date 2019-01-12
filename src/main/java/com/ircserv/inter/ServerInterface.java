package com.ircserv.inter;

import com.ircserv.metier.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServerInterface extends Remote{

    /**
     * @return the list of all message of a server
     */
    List<Message> getMessages() throws RemoteException;

    /**
     * @param nbLastMessage number of messages you want to get
     * @return a list of the nb last messages
     */
    List<Message> getMessages(int nbLastMessage) throws RemoteException;

    /**
     * add the message to the message list (bdd / serverList / ...)
     * @param userId the id of the user
     * @param message the content of the message
     */
    void send(int userId, String message) throws RemoteException;

    /**
     * upload a file to send to other people
     * @param userId the id of the user
     * @param data the file in bytes
     * @param filename the name of the file
     */
    void uploadFile(int userId, byte[] data, String filename) throws RemoteException;

    /**
     * @return a list of user <b>not link</b> to a server
     */
    List<Utilisateur> getAllUserNotInServer() throws RemoteException;

    /**
     * @return a list of user <b>link</b> to a server
     */
    List<Utilisateur> getAllUserInServer() throws RemoteException;

    /**
     * add a user to a server
     * @param utilisateur the user to add
     */
    void linkUserToServer(Utilisateur utilisateur) throws RemoteException;

    /**
     * delete a user to a server
     * @param utilisateur the user on the server
     */
    void unlinkUserToServer(Utilisateur utilisateur) throws RemoteException;

    /**
     * @param utilisateur the user on the server
     * @return right value assign to a user on a server
     */
    Droit getDroit(int utilisateur) throws RemoteException;

    /**
     * change right value assign to a user on a server
     * @param utilisateur the user on the server
     * @param droit the new right
     */
    void setDroit(Utilisateur utilisateur, Droit droit) throws RemoteException;

    /**
     * @return all  right of a server
     */
    List<UtilisateurDroitServer> getAllDroit() throws RemoteException;
}
