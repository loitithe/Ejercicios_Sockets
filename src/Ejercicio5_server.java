import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio5_server {

    public static void main(String[] args) {
        int numeroPuerto = 6000;
        ServerSocket serverSocket = null;
        OutputStream salida = null;
        try {
            serverSocket = new ServerSocket(numeroPuerto);
            System.out.println("Esperando..."); //Esperando conexión
            Socket [] socket = new Socket[3];
            for (int i = 0; i < socket.length; i++) {
                socket[i]=serverSocket.accept();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
