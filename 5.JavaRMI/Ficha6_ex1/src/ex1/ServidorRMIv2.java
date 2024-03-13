//author: Daniel Santos
package ex1;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ServidorRMIv2 extends UnicastRemoteObject implements InterfaceContador{

    int contador;
    static String nome;

    ServidorRMIv2() throws RemoteException {
        super();
        contador = 0;
    }

    public static void main(String[] args) {

        try {
            ServidorRMIv2 serv = new ServidorRMIv2();
            
            LocateRegistry.createRegistry(1099);
            
            nome = inputString("Nome do objeto remoto:");
            
            Naming.rebind(nome,serv);            
            //Naming.rebind("//localhost:1099/meuContador", serv);
            
            System.out.println("servidor RMI iniciado");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int obtemValor() throws RemoteException {
        return contador;
    }

    public void incrementa() throws RemoteException {
        ++contador;
    }

    public void soma(int num) throws RemoteException {
        contador += num;
    }
    
    public static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }
}
