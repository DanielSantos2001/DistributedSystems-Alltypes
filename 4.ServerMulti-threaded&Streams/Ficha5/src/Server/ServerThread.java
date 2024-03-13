//author:Daniel Santos
package Server;

import Mensagem.Mensagem;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {

    ObjectOutputStream output;
    ObjectInputStream input;
    Mensagem mensagem;
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        server(socket);
    }

    public void server(Socket socket) {

        while (true) {

            try {
                try {
                    output = new ObjectOutputStream(socket.getOutputStream());
                    input = new ObjectInputStream(socket.getInputStream());
                    mensagem = (Mensagem) input.readObject();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException e) {
                    Main.listaNome.remove(mensagem.getNome());
                    return;
                } catch (EOFException e) {
                    Main.listaNome.remove(mensagem.getNome());
                    return;
                }

                if (mensagem.getOperacao() == 0) {
                    for (int i = 0; i < Main.listaNome.size(); i++) {
                        if (Main.listaNome.get(i).equals(mensagem.getNome())) {

                            mensagem = new Mensagem(4, mensagem.getNome(), "");
                            output.writeObject(mensagem);

                            input.close();
                            output.close();
                            socket.close();
                            return;
                        }
                    }

                    Main.listaNome.add(mensagem.getNome());
                    mensagem = new Mensagem(3, mensagem.getNome(), "");
                    output.writeObject(mensagem);

                } else if (mensagem.getOperacao() == 1) {
                        output.writeObject(mensagem);

                } else if (mensagem.getOperacao() == 2) {
                    
                    input.close();
                    output.close();
                    socket.close();
                    return;

                }

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
