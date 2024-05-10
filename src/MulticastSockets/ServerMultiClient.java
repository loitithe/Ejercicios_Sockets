package MulticastSockets;

import java.io.IOException;
import java.net.*;

public class ServerMultiClient {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor;
        servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado ...");
        while(true){
            Socket cliente = new Socket();
            cliente= servidor.accept();
            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start();
        }
    }
}
