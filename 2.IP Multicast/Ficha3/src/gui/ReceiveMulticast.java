//author: Daniel Santos
package gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class ReceiveMulticast implements Runnable {

    String ip;
    int port;
    JTextArea textarea;
    boolean log = false;
    Thread t;
    Socket s;

    ReceiveMulticast(String ip, int port, JTextArea textarea) {
        this.ip = ip;
        this.port = port;
        this.textarea = textarea;
        

    }

    ReceiveMulticast() {

    }

    public void run() {
        receive(ip, port, textarea);
    }

    public void login(String ip, int port, JTextArea textarea) {
        ReceiveMulticast rm = new ReceiveMulticast(ip, port, textarea);
        t = new Thread(rm);
        t.start();
    }

    public void receive(String ip, int port, JTextArea textarea) {
        try {
            InetAddress in = InetAddress.getByName(ip);
            MulticastSocket s = new MulticastSocket(port);
            s.joinGroup(in);
            while (true) {
            byte buf[] = new byte[1000];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            //while (true) {
                s.receive(dp);
                String str = new String(dp.getData());
                if (str.contains(":")) {
                    String[] message = str.split(":");
                    textarea.append(message[0] + " (" + dp.getAddress().getHostAddress() + "): " + message[1] + "\n");
                    textarea.setCaretPosition(textarea.getDocument().getLength());
                }
                str = "";
                
            }
        } catch (IOException e) {
        }
    }

    public void logout() {
        try {
            t.stop();
            s.close();
        } catch (Exception e) {
        }
    }

}
