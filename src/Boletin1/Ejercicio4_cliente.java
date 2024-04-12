package Boletin1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio4_cliente {
    static String Host = "localhost";
    static int puerto = 6000;

    //TODO
    public static void main(String[] args) {
        System.out.println("Iniciando cliente ... ");
        try {
            Socket cliente = new Socket(Host, puerto);
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(pedirInt("Introduce el numero a calcular"));

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println(flujoEntrada.readInt());

            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int pedirInt(String mensaje) {
        Scanner sc= new Scanner(System.in);
            int entrada = -1;
        while (entrada==-1) {
            System.out.println(mensaje);
            try {
                entrada = sc.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }
            sc.nextLine();

        }
            return entrada;

    }
    public static String pedirString(String mensaje) {
        Scanner sc= new Scanner(System.in);
        while (true) {
            System.out.println(mensaje);
            String entrada = "";
            try {
                entrada = sc.next();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }
            sc.nextLine();

            return entrada;
        }

    }
}
