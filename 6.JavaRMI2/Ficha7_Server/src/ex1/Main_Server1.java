//author: Daniel Santos
package ex1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main_Server1 extends UnicastRemoteObject implements InterfaceLeilao {

    static Boolean b;
    static Item item;
    static Item itemArray[];
    static ArrayList<Item> itemList = new ArrayList<Item>();
    static int id;

    Main_Server1() throws RemoteException {
        super();
        b = false;
        item = null;
        id = 1;
    }

    public static void main(String[] args) {
        try {
            Main_Server1 serv = new Main_Server1();

            LocateRegistry.createRegistry(1099);

            //nome = inputString("Nome do objeto remoto:");
            //Naming.rebind(nome, serv);
            Naming.rebind("teste", serv);

            System.out.println("servidor RMI iniciado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized Item obtemItem(int id) throws RemoteException {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getId() == id) {
                    return itemList.get(i);
                }
            }
        }
        return null;
    }

    public synchronized Item[] obtemTodosItens() throws RemoteException {
        if (itemList != null) {
            itemArray = itemList.toArray(new Item[0]);
            return itemArray;
        }
        return null;
    }

    public synchronized boolean adicionaItem(Item novo) throws RemoteException {
        if (novo != null) {
            novo.setId(id);
            novo.setPrecoAtual(novo.getPrecoMinimo());
            novo.setNumeroOfertas(0);
            novo.setTerminado(false);
            itemList.add(novo);
            id++;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean removeItem(int id) throws RemoteException {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getId() == id) {
                    itemList.remove(itemList.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized boolean oferece(int id, float valor) throws RemoteException {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                if ((itemList.get(i).getId() == id) && itemList.get(i).getPrecoAtual() <= valor) {
                    if (itemList.get(i).getDataHoraFecho().before(Calendar.getInstance())) {
                        itemList.get(i).setTerminado(true);
                        return false;
                    }
                    itemList.get(i).setPrecoAtual(valor);
                    itemList.get(i).setNumeroOfertas(itemList.get(i).getNumeroOfertas() + 1);
                    return true;
                }
            }
        }
        return false;
    }

}
