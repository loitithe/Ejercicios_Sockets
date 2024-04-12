package Boletin1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Ejercicio6_server {

    private static int numeroPuerto = 6000;

    public static void main(String[] args) {
        /* Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta
            introducir un asterisco. Las cadenas se enviarán a un programa servidor. El programa servidor
            aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente el
            número de caracteres de la misma. El programa servidor finalizará cuando reciba un asterisco
            como cadena
          */
        Scanner sc = new Scanner(System.in);
        Socket clienteOn;
        try  {
            ServerSocket serverSocket = new ServerSocket(numeroPuerto);
            InputStream entrada = null;
            clienteOn = serverSocket.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            DataInputStream flujoEntrada = new DataInputStream(entrada);

            OutputStream salida = null;
            salida = clienteOn.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            clienteOn = null;
            clienteOn = serverSocket.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            while (!clienteOn.isClosed()) {
                entrada = clienteOn.getInputStream();
                flujoEntrada = new DataInputStream(entrada);
                String cadena =flujoEntrada.readUTF();
                int caracteres = cadena.length();
                flujoSalida.writeInt(caracteres);
            }
            clienteOn.shutdownOutput();
            clienteOn.close();
            serverSocket.close();

        } catch (SocketException s){
            System.exit(1);
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


}
