//author: Daniel Santos
package ex1;

import java.rmi.*;

public interface InterfaceLeilao extends Remote{

    Item obtemItem(int id) throws RemoteException;

    Item[] obtemTodosItens() throws RemoteException;

    boolean adicionaItem(Item novo) throws RemoteException;

    boolean removeItem(int id) throws RemoteException;

    boolean oferece(int id, float valor) throws RemoteException;
    
    void login(int id,InterfaceCallBack iClient) throws RemoteException;
}
