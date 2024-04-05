import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPCliente {
    static String Host = "localhost";
    static int puerto = 6000;

    //TODO
    public static void main(String[] args) {
        System.out.println("Iniciando cliente ... ");
        try {
            Socket cliente = new Socket(Host, puerto);
            InetAddress i = cliente.getInetAddress();
            System.out.println(cliente.getLocalAddress());
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
