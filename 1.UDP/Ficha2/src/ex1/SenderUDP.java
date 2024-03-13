//author: Daniel Santos
package ex1;

import java.net.*;
import java.util.Scanner;

public class SenderUDP {

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName(inputString("Introduza o endereço destino: "));
            int port = inputInt("Introduza o porto destino: ");
            while (true) {
                String s = inputString("Introduza a mensagem que deseja enviar: ");
                byte[] data = s.getBytes();
                if("sair".equals(s)){
                    System.out.println("A sair...");
                    System.exit(0);
                }
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
                DatagramSocket socket = new DatagramSocket();
                socket.send(dp);
            }
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

    private static int inputInt(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        int read = myObj.nextInt();
        return read;
    }

}
