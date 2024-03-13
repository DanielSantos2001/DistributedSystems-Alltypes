//author: Daniel Santos
package ex1;

import java.net.*;
import java.util.Scanner;

public class ReceiverUDP {

    public static void main(String[] args) {
        byte[] inbuf = new byte[1000];

        try {
            int port = input("Introduza o porto: ");
            System.out.println("A receber...");
            DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
            DatagramSocket socket = new DatagramSocket(port);
            while (true) {
                socket.receive(dp);
                String conteudo = new String(dp.getData(), 0, dp.getLength());
                byte[] data = conteudo.getBytes();
                System.out.println("IP: " + dp.getAddress() + " Porto: " + dp.getPort() + " Tamanho da mensagem(bytes):" + data.length + " Mensagem: " + conteudo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static int input(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        int read = myObj.nextInt();
        return read;
    }

}
