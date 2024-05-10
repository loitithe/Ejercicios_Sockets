package Boletin2.Ejercicio4TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*Usando sockets TCP, realiza un programa servidor que espere un datagrama de un cliente. El
cliente le envía un objeto Persona que previamente había inicializado. El servidor modifica los
datos del objeto Persona y se lo envía de vuelta al cliente. Visualiza los datos del objeto
Persona tanto en el programa cliente cuando los envía y los recibe como en el programa
servidor cuando los recibe y los envía modificados.*/
public class TCPServer {
    public static void main(String[] args) {
        int puerto = 6000;
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Esperando al cliente");
            Socket client = servidor.accept();

            ObjectOutputStream outObjeto = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inObjeto = new ObjectInputStream(client.getInputStream());

            Persona dato = (Persona) inObjeto.readObject();
            dato.setEdad(dato.getEdad() + 1);
            dato.setNombre(dato.getNombre() + " modificado");
            // enviando objeto
            outObjeto.writeObject(dato);
            System.out.println("Dato enviado");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
