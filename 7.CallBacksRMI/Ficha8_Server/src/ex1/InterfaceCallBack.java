//author: Daniel Santos
package ex1;

import java.rmi.*;

public interface InterfaceCallBack extends Remote {

    void notificacaoItem(String str) throws RemoteException;
}