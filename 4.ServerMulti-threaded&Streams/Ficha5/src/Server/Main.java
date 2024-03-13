//author: Daniel Santos
package Server;

import Mensagem.Mensagem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author surfc
 */
public class Main {

    static ServerThread serverThread;
    static Socket socket;
    public static final ArrayList<ServerThread> listaThread = new ArrayList<>();
    public static ArrayList<String> listaNome = new ArrayList<String>();
    
    public static void main(String[] args) {
        try {

            ServerSocket serversocket = new ServerSocket(4000);

            while (true) {
                socket = serversocket.accept();
                
                
                
                serverThread = new ServerThread(socket);
                listaThread.add(serverThread);
                serverThread.start();
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e);
            e.printStackTrace();
        }
    }
}
