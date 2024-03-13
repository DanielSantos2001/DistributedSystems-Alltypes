//author: Daniel Santos
package ex1;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class ClienteRMIv2 {

    static String nome;
    static String list[];

    public static void main(String[] args) {
        try {
            
            LocateRegistry.createRegistry(1100);

            Registry registry = LocateRegistry.getRegistry();
            list = registry.list();
            
            System.out.println("Contadores disponíveis:");
            for(int i=0;i<list.length;i++){
                System.out.println(list[i]);
            }
            
            nome = inputString("Nome do contador:");

            String url = "//localhost:1099/" + nome;

            InterfaceContador objRemoto = (InterfaceContador) Naming.lookup(url);
            System.out.println("Valor inicial: " + objRemoto.obtemValor());
            
            objRemoto.incrementa();
            
            objRemoto.soma(5);

            System.out.println("Valor final: " + objRemoto.obtemValor());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }
}
