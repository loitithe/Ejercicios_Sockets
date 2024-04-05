import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio4_server {
    public static void main(String[] args) {
        int numeroPuerto = 6000;
        try {
            ServerSocket serverSocket = new ServerSocket(numeroPuerto);
            System.out.println("Esperando..."); //Esperando conexión
            Socket clienteOn = null;
            clienteOn = serverSocket.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");
            //flujo entrada cliente
            InputStream entrada = null;
            entrada = clienteOn.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            int numero=flujoEntrada.readInt();
            int cuadradoNumero = numero*numero;
            //cliente envia mensaje

            OutputStream salida = null;
            salida = clienteOn.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            //envia mensaje al cliente

            flujoSalida.writeInt(cuadradoNumero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
