package Boletin2.Ejercicio1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            socket = new Socket("localhost", 6000);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String idProfesorStr;
            int idProfesor;

            do {
                // Solicitar al usuario el ID del profesor que desea consultar
                System.out.print("Introduce el ID del profesor que deseas consultar (Escribe * para salir): ");
                idProfesorStr = scanner.nextLine();

                if (!idProfesorStr.equals("*")) {
                    idProfesor = Integer.parseInt(idProfesorStr);
                    outputStream.writeInt(idProfesor);
                    outputStream.flush();

                    Profesor profesor = (Profesor) inputStream.readObject();
                    if (profesor.getIdProfesor() == 0) {
                        System.out.println("El profesor con ID " + idProfesor + " no está registrado.");
                    } else {
                        System.out.println("Profesor recibido del servidor:");
                        System.out.println(profesor.toString());
                    }
                }
            } while (!idProfesorStr.equals("*"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}