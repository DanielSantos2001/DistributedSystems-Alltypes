//author: Daniel Santos
package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {
        try {
            String ip = inputString("Ip: ");
            Socket socket = new Socket(ip, 13);
            String answer;
            
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());
            
            answer = input.readUTF();
            System.out.println(answer);
            
            while(true){
                
            String s = inputString("Mensagem: ");
            
            if (s.equals("end")) {
                output.writeUTF("end");
                break;
            }
            
            output.writeUTF(s);

            answer = input.readUTF();
            System.out.println("Resposta: " + answer);
            }
                  
            //answer = input.readUTF();
            //System.out.println(answer);
            input.close();
            output.close();
            socket.close(); 
            System.out.println("A encerrar...");
            
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            e.printStackTrace();
        }
    }

    private static String inputString(String n) {
        Scanner myObj = new Scanner(System.in);
        System.out.print(n);
        String read = myObj.nextLine();
        return read;
    }
}
