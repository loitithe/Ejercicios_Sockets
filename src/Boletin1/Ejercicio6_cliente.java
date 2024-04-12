package Boletin1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio6_cliente {
    public static void main(String[] args) {
           /* Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta
            introducir un asterisco. Las cadenas se enviarán a un programa servidor. El programa servidor
            aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente el
            número de caracteres de la misma. El programa servidor finalizará cuando reciba un asterisco
            como cadena
          */
        String Host = "localhost";
        int puerto = 6000;
        Socket cliente = null;
        System.out.println("Iniciando cliente ... ");
        try {
            cliente = new Socket(Host, puerto);
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            pedirString(cliente);
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pedirString(Socket clientOn) {

        Scanner sc = new Scanner(System.in);
        String mensaje = "";
        while (!mensaje.equals("*")) {
            try {
                OutputStream salida = null;
                salida = clientOn.getOutputStream();
                DataOutputStream daOs = new DataOutputStream(salida);
                daOs.writeUTF(mensaje);
                System.out.println("numero de caracteres");
                System.out.println("Introduce cadena");
                mensaje = sc.nextLine();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }

        }

    }
}
