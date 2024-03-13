//author: Daniel Santos
package ex2;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Server extends UnicastRemoteObject implements InterfaceMoeda {

    static float USD;
    static float EUR;
    static float cotacao;
    static String time;
    static String nome;

    Server() throws RemoteException {
        super();
        USD = 0;
        EUR = 0;
        cotacao = 1;
        time = "N/A";
    }

    public static void main(String[] args) {
        try {
            Server serv = new Server();

            LocateRegistry.createRegistry(1099);

            //nome = inputString("Nome do objeto remoto:");

            //Naming.rebind(nome, serv);
            Naming.rebind("teste", serv);

            System.out.println("servidor RMI iniciado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String obterCotação() throws RemoteException {
        String output;
        output = "1USD = " + cotacao + "EUR , " + time;
        return output;
    }

    public float UsdToEur(float num) throws RemoteException {
        EUR = cotacao * num;
        return EUR;
    }

    public float EurToUsd(float num) throws RemoteException {
        USD = (1/cotacao) * num;
        return USD;
    }

    public void alterarCotacao(float num) throws RemoteException {
        cotacao = num;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        time = currentDateTime.format(formatter);
    }

    public static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }

}
