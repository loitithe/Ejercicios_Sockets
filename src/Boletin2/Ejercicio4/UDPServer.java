package Boletin2.Ejercicio4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado. Esperando datagramas de clientes...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Persona persona = (Persona) objectInputStream.readObject();

                // Modificar los datos de la persona
                persona.setEdad(persona.getEdad() + 1);
                persona.setNombre(persona.getNombre() + " modificado");

                System.out.println("Datos recibidos del cliente: " + persona);

                // Enviar el objeto Persona modificado de vuelta al cliente
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(persona);

                byte[] sendData = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Datos enviados al cliente: " + persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
