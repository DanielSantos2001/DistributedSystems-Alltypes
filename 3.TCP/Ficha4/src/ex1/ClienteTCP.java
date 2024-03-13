//author: Daniel Santos
package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String args[]) {
        try {
            String ip = inputString("Ip: ");
            Socket socket = new Socket(ip, 13);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            
            byte[] b = input.readAllBytes();
            String answer = new String(b);
            System.out.println("Resposta:" + answer);
            
            input.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    private static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }
}
