package Boletin2.Ejercicio4TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*Usando sockets TCP, realiza un programa servidor que espere un datagrama de un cliente. El
cliente le envía un objeto Persona que previamente había inicializado. El servidor modifica los
datos del objeto Persona y se lo envía de vuelta al cliente. Visualiza los datos del objeto
Persona tanto en el programa cliente cuando los envía y los recibe como en el programa
servidor cuando los recibe y los envía modificados.*/
public class TCPClient {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        Socket client = null;
        try {
            client = new Socket(host, puerto);
            System.out.println("PROGRAMA CLIENTE INICIADO");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream inObject = new ObjectInputStream(client.getInputStream());
             ObjectOutputStream outObject = new ObjectOutputStream(client.getOutputStream())) {
            outObject.writeObject(rellenarPersona());

            Persona dato = (Persona) inObject.readObject();

            System.out.println(dato);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static Persona rellenarPersona() {

        try {
            System.out.println("Introduce el nombre");
            String nombre = sc.nextLine();
            System.out.println("Introduce la edad");
            int edad = sc.nextInt();
            return new Persona(nombre, edad);
        } catch (Exception e) {
        }
        return null;
    }
}
