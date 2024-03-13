//author: Daniel Santos
package ex2;

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorDaytime {

    public static void main(String[] args) {
        byte[] inbuf = new byte[1000];

        try {
            int port = 13;
            System.out.println("A receber...");
            
            DatagramPacket dpReceive = new DatagramPacket(inbuf, inbuf.length);
            DatagramSocket socket = new DatagramSocket(port);
            while (true) {
                //receber
                socket.receive(dpReceive);
                String conteudo = new String(dpReceive.getData(), 0, dpReceive.getLength());
                System.out.println("IP: " + dpReceive.getAddress() + " Porto: " + dpReceive.getPort());
                
                //enviar
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String s = currentDateTime.format(formatter);
                byte[] data = s.getBytes();
                DatagramPacket dpSend = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
                socket.send(dpSend);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
