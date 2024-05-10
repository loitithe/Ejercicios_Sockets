package Boletin1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio3_ServerTCP {

    public static void main(String[] args) {
        int port = 6000;
        ServerSocket serverSocket;
        int contador = 0;
        try {
            serverSocket = new ServerSocket(port);
            Socket cliente = null;
            System.out.println("Esperando cliente..");
            while (contador < 2) {
                cliente = serverSocket.accept();
                // recibiendo mensaje del cliente
                System.out.println(cliente.toString());
                System.out.println(cliente.getPort());
                System.out.println(cliente.getLocalPort());
                System.out.println(cliente.getLocalPort());
                contador++;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
