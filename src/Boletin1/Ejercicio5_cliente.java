package Boletin1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Ejercicio5_cliente {
    public static void main(String[] args) {
         String Host = "localhost";
         int puerto = 6000;
        System.out.println("Iniciando cliente ... ");
        try {
            Socket cliente = new Socket(Host, puerto);
            InputStream aux = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(aux);
            System.out.println(flujoEntrada.readInt());

            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
