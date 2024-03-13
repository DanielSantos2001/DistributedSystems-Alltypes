//author: Daniel Santos
package gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendMulticast {

    public void send(String ip, int port, String name, String message) {

        try {
            InetAddress in = InetAddress.getByName(ip);
            MulticastSocket s = new MulticastSocket();
            String m = name + ":" + message;
            byte[] send = m.getBytes();
            DatagramPacket dp = new DatagramPacket(send, send.length, in, port);
            s.setTimeToLive(5);
            s.send(dp);
            s.close();
        } catch (IOException e) {
        }
    }

}
