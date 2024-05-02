package Boletin2.Ejercicio4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Crear una persona
            Persona persona = new Persona("Juan", 30);

            // Enviar la persona al servidor
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(persona);

            byte[] sendData = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            System.out.println("Datos enviados al servidor: " + persona);

            // Recibir la persona modificada del servidor
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Persona personaModificada = (Persona) objectInputStream.readObject();

            System.out.println("Datos recibidos del servidor: " + personaModificada);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
