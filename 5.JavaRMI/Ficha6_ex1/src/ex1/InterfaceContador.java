//author: Daniel Santos
package ex1;

import java.rmi.*;

public interface InterfaceContador extends Remote {

    public int obtemValor() throws RemoteException;

    public void incrementa() throws RemoteException;

    public void soma(int num) throws RemoteException;
}
