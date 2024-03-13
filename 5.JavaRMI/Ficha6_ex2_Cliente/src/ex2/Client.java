//author: Daniel Santos
package ex2;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    static String nome;
    static String ip;
    static String list[];
    static int opcao;

    public static void main(String[] args) {
        try {

            ip = inputString("Introduza o ip:");

            LocateRegistry.createRegistry(1100);

            /*Registry registry = LocateRegistry.getRegistry();
            list = registry.list();

            System.out.println("Servers disponíveis:");
            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i]);
            }

            nome = inputString("Nome do server:");*/

            String url = "//" + ip + ":1099/teste";

            InterfaceMoeda objRemoto = (InterfaceMoeda) Naming.lookup(url);
            System.out.println("Conectado...");

            while (true) {
                System.out.println("1.Ver cotação  2.Dolar para Euro  3.Euro para Dolar  4.Alterar cotação  0.Sair");
                opcao = inputInt("Introduza a opção que deseja:");

                if (opcao == 0) {
                    break;
                } else if (opcao == 1) {
                    System.out.println(objRemoto.obterCotação());
                    
                } else if (opcao == 2) {
                    System.out.println(objRemoto.UsdToEur(inputFloat("Quantia dolar: ")));
                    
                } else if (opcao == 3) {
                    System.out.println(objRemoto.EurToUsd(inputFloat("Quantia euro: ")));
                    
                } else if (opcao == 4) {
                    objRemoto.alterarCotacao(inputFloat("Cotação: "));
                    
                } else {
                    System.out.println("Opção inválida");
                }
            }

            System.out.println("Adeus...");
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

    public static float inputFloat(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        float read = myObj.nextFloat();
        return read;
    }
    
    public static int inputInt(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        int read = myObj.nextInt();
        return read;
    }
}
