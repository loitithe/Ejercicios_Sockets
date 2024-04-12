package Boletin1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPCliente {
    static String Host = "localhost";
    static int puerto = 6000;

    //TODO
    public static void main(String[] args) {
        System.out.println("Iniciando cliente ... ");
        Socket cliente=null;
        try {
             cliente = new Socket(Host, puerto);
//            InetAddress i = cliente.getInetAddress();
//            System.out.println(cliente.getLocalAddress());
//            cliente.close();
            InputStream aux = cliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            System.out.println("server :"+flujo.readUTF());
            System.out.println("cerrando cliente "+cliente.getPort());
            cliente.shutdownOutput();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
