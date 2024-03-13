//author: Daniel Santos
package Client;

import Mensagem.Mensagem;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Client extends Thread {

    Mensagem mensagem;
    ObjectOutputStream output;
    ObjectInputStream input;
    JTextArea textarea;
    Socket socket;

    public Client(JTextArea textarea, Socket socket) {
        this.textarea = textarea;
        this.socket = socket;
    }

    public void run() {
        receber(textarea, socket);
    }

    public void receber(JTextArea textarea, Socket socket) {

        while (true) {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                mensagem = (Mensagem) input.readObject();
                System.out.println(mensagem);
                
                textarea.append(mensagem.getNome() + ": " + mensagem.getTexto() + "\n");
                textarea.setCaretPosition(textarea.getDocument().getLength());

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviar(String nome, String texto, Socket socket) {

        mensagem = new Mensagem(1, nome, texto);

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean login(String name, Socket socket) {

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        mensagem = new Mensagem(0, name, "");
        try {
            output.writeObject(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            mensagem = (Mensagem) input.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (mensagem.getOperacao() == 3) {
            return true;
        } else if (mensagem.getOperacao() == 4) {
            return false;
        } else {
            return false;
        }
    }

    public void logOut(String name, Socket socket) {
        mensagem = new Mensagem(3, name, "");

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
