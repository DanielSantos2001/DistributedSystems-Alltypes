//author: Daniel Santos
package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serversocket = new ServerSocket(13);
            String t;
            String command = "";
            DateTimeFormatter formatter;
            LocalDateTime currentDateTime;
            Socket socket;
            DataOutputStream output;
            DataInputStream input;

            while (true) {

                command = "";
                socket = serversocket.accept();
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());

                currentDateTime = LocalDateTime.now();
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                t = currentDateTime.format(formatter);
                output.writeUTF(t);
 
                while (!command.equals("end")) {

                    command = input.readUTF();

                    currentDateTime = LocalDateTime.now();

                    System.out.println("Received command: " + command + " Local IP: " + socket.getLocalAddress().getHostAddress()
                            + " Local Port: " + socket.getLocalPort()
                            + " Remote IP: " + socket.getInetAddress().getHostAddress()
                            + " Remote Port: " + socket.getPort() + "\n");

                    switch (command) {
                        case "datetime": {
                            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            t = currentDateTime.format(formatter);
                            output.writeUTF(t);
                            break;
                        }
                        case "date": {
                            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            t = currentDateTime.format(formatter);
                            output.writeUTF(t);
                            break;
                        }
                        case "time": {
                            formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            t = currentDateTime.format(formatter);
                            output.writeUTF(t);
                            break;
                        }
                        case "help": {
                            t = "datetime/date/time";
                            output.writeUTF(t);
                            break;
                        }
                        case "end": {
                            //output.writeUTF("Connection will be closed");
                            break;
                        }
                        default:
                            t = "invalid command";
                            output.writeUTF(t);
                    }

                }

                input.close();
                output.close();
                socket.close();

            }

        } catch (Exception e) {
            System.out.println("Erro:" + e);
            e.printStackTrace();
        }
    }
}
