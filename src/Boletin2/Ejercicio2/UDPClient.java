package Boletin2.Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // Crear un socket UDP
            DatagramSocket clientSocket = new DatagramSocket();

            // Obtener la dirección IP del servidor y el puerto
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Leer la entrada del usuario
                System.out.print("Introduce un mensaje (Escribe * para salir): ");
                String message = scanner.nextLine();

                // Convertir la cadena a bytes
                byte[] sendData = message.getBytes();

                // Crear el paquete UDP para enviar al servidor
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // Enviar el paquete al servidor
                clientSocket.send(sendPacket);

                // Si el usuario envía "*", finalizar el cliente
                if (message.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                // Preparar el buffer para recibir la respuesta del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Establecer un tiempo de espera de 5000ms para recibir la respuesta del servidor
                clientSocket.setSoTimeout(5000);

                try {
                    // Recibir la respuesta del servidor
                    clientSocket.receive(receivePacket);

                    // Convertir los datos recibidos a una cadena
                    String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Respuesta del servidor: " + modifiedSentence);
                } catch (SocketTimeoutException e) {
                    // Si no se reciben datos antes del tiempo de espera, mostrar un mensaje
                    System.out.println("El paquete se ha perdido.");
                }
            }

            // Cerrar el socket del cliente
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}