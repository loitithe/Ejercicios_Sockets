package Boletin2.Ejercicio5;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Introduce el ID del alumno que deseas consultar (Escribe * para salir): ");
                String idAlumno = reader.readLine();

                if (idAlumno.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                // Enviar el ID del alumno al servidor
                byte[] sendData = idAlumno.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Recibir el objeto Alumno del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Alumno alumno = (Alumno) objectInputStream.readObject();

                System.out.println("Datos recibidos del servidor: " + alumno);
            }

            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
