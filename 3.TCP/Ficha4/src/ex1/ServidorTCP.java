//author: Daniel Santos
package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorTCP {

    public static void main(String[] args) {
        try {
            ServerSocket serversocket = new ServerSocket(13);
            while (true) {
                Socket socket = serversocket.accept();
                   
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String t = currentDateTime.format(formatter);
                
                output.writeChars(t);
                
                output.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
    }
}
