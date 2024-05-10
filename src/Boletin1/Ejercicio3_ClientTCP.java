package Boletin1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejercicio3_ClientTCP {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;
        try (Socket client = new Socket(host, port)) {
            InetAddress i= client.getInetAddress ();
            System.out.println ("Puerto local: "+ client.getLocalPort());
            System.out.println ("Puerto Remoto: "+ client.getPort());
            System.out.println ("Nombre Host/IP: "+ client.getInetAddress());
            System.out.println ("Host Remoto: "+ i.getHostName().toString());
            System.out.println ("IP Host Remoto: "+ i.getHostAddress().toString());

            client.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
