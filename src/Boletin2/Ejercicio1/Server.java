package Boletin2.Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Profesor[] profesores = new Profesor[5];
    private static int nextId = 1;

    public static void main(String[] args) {
        // Inicializar array de profesores
        for (int i = 0; i < profesores.length; i++) {
            profesores[i] = new Profesor();
            profesores[i].setIdProfesor(i + 1);
        }

        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                int idCliente = nextId++;
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                int idProfesor = inputStream.readInt();
                System.out.println("ID del profesor recibido: " + idProfesor); // Verificar si recibimos el ID correctamente

                Profesor profesor = buscarProfesor(idProfesor);
                outputStream.writeObject(profesor);
                outputStream.flush(); // Asegurar que se envíen todos los datos al cliente
                System.out.println("Profesor enviado al cliente."); // Verificar si estamos enviando el profesor correctamente

                // Cerrar el socket del cliente después de enviar la respuesta
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Profesor buscarProfesor(int idProfesor) {
        for (Profesor profesor : profesores) {
            if (profesor.getIdProfesor() == idProfesor) {
                return profesor;
            }
        }
        // Si el profesor no se encuentra, devolver un profesor vacío
        return new Profesor();
    }
}