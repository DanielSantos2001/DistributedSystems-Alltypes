//author:Daniel Santos
package ex1;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Scanner;

public class Main_ClientConsulta extends UnicastRemoteObject implements InterfaceCallBack {

    static String ip;
    static int opcao;
    static Item item;
    static Item itemArray[];
    static String descricao;
    static Calendar dataHoraFecho;
    static float precoMinimo;
    static float precoAtual;
    static int id;

    Main_ClientConsulta() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            Main_ClientConsulta client = new Main_ClientConsulta();
            
            while (true) {
                try {
                    ip = inputString("Introduza o ip: ");
                    InetAddress.getByName(ip);
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            String url = "//" + ip + ":1099/teste";
            InterfaceLeilao objRemoto = (InterfaceLeilao) Naming.lookup(url);

            System.out.println("Conectado...");

            System.out.println("Leilões disponíveis:");
            itemArray = objRemoto.obtemTodosItens();
            for (int i = 0; i < itemArray.length; i++) {
                System.out.println(itemArray[i].getId());
            }

            objRemoto.login(inputInt("Introduza id do leilão: "), client);

            while (true) {
                if (inputString("Introduza 'sair' para Sair: ").equals("sair")) {
                    break;
                }
            }

            System.out.println("Adeus...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void notificacaoItem(String str) {
        System.out.println(str);
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
