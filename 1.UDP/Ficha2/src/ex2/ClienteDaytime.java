//author: Daniel Santos
package ex2;

import java.net.*;
import java.util.Scanner;
import java.net.SocketTimeoutException;

public class ClienteDaytime {

    public static void main(String[] args) {
        byte[] inbuf = new byte[1000];
        try {
            //enviar
            InetAddress ia = InetAddress.getByName(inputString("Introduza o endereço destino: "));
            int port = 13;
            String s = "";
            byte[] data = s.getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, ia, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(dpSend);

            //receber
            DatagramPacket dpReceive = new DatagramPacket(inbuf, inbuf.length);
            try {
                socket.setSoTimeout(4000);
                socket.receive(dpReceive);
            } catch (SocketTimeoutException e) {
                System.out.println("Sem Resposta...");
            }

            String conteudo = new String(dpReceive.getData(), 0, dpReceive.getLength());
            System.out.println(conteudo);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }

}
