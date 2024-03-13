//author: Daniel Santos
package ex1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Main_Server extends UnicastRemoteObject implements InterfaceLeilao {

    static Boolean b;
    static Item item;
    static Item itemArray[];
    static ArrayList<Item> itemList = new ArrayList<Item>();
    static InterfaceCallBack iClient;
    static int id;
    static int sessionID;
    static String url;
    static ArrayList<ClienteConsulta> listClient = new ArrayList<ClienteConsulta>();
    static ClienteConsulta client;

    Main_Server() throws RemoteException {
        super();
        b = false;
        item = null;
        id = 1;
    }

    public static void main(String[] args) {
        try {
            Main_Server serv = new Main_Server();

            LocateRegistry.createRegistry(1099);

            url = "//localhost:1099/teste";
            Naming.rebind(url, serv);

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

                    for (int j = 0; j < listClient.size(); j++) {
                        try {
                            if (listClient.get(j).getId() == id) {
                                String str = "\n Numero Ofertas: " + itemList.get(i).getNumeroOfertas() + " Preço atual: " + itemList.get(i).getPrecoAtual() + " Data: " + Calendar.getInstance().getTime();
                                listClient.get(j).getInterfaceCallBack().notificacaoItem(str);
                            }
                        } catch (Exception e) {
                            listClient.remove(j);
                        }

                    }

                    return true;
                    
                }
            }
        }
        return false;
    }

    public synchronized void login(int id, InterfaceCallBack iClient) {
        client = new ClienteConsulta(id, iClient);
        listClient.add(client);
    }

}
