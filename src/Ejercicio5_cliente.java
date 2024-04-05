import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ejercicio5_cliente {
    public static void main(String[] args) {
         String Host = "localhost";
         int puerto = 6000;
        System.out.println("Iniciando cliente ... ");
        try {
            Socket cliente = new Socket(Host, puerto);


            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println(flujoEntrada.readInt());

            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
