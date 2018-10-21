package inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MenuInterface extends Remote {
    int createNewServer() throws RemoteException;
    void deleteServer(int nbServ) throws RemoteException;

}
