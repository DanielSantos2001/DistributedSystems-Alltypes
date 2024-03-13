//author: Daniel Santos
package ex2;

import java.rmi.*;

public interface InterfaceMoeda extends Remote {

    public String obterCotação() throws RemoteException;

    public float UsdToEur(float num) throws RemoteException;
    
    public float EurToUsd(float num) throws RemoteException;

    public void alterarCotacao(float num) throws RemoteException; 
}
