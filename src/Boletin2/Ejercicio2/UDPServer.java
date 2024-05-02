package Boletin2.Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Crear un socket UDP en el puerto 12345
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado. Esperando conexiones...");

            while (true) {
                // Preparar el buffer para recibir datos
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Recibir el paquete del cliente
                serverSocket.receive(receivePacket);

                // Obtener la dirección IP y el puerto del cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Convertir los datos recibidos a una cadena
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Mensaje recibido del cliente: " + sentence);

                // Convertir la cadena a mayúsculas
                String capitalizedSentence = sentence.toUpperCase();

                // Preparar el buffer para enviar los datos de vuelta al cliente
                byte[] sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Enviar la respuesta al cliente
                serverSocket.send(sendPacket);

                // Si el cliente envía "*", finalizar el servidor
                if (sentence.equals("*")) {
                    System.out.println("Servidor finalizado.");
                    break;
                }
            }

            // Cerrar el socket del servidor
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}