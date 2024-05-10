package Boletin2.Ejercicio4UDP;

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
            int serverPort = 6000;

            Persona dato = new Persona("Juan", 30);
            // Enviar la persona al servidor
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(dato);

                byte[] enviados = outputStream.toByteArray();
                DatagramPacket paqueteEnviado = new DatagramPacket(enviados, enviados.length, serverAddress, serverPort);
                clientSocket.send(paqueteEnviado);

                System.out.println("Datos enviados al servidor: " + dato);
                // Recibir la persona modificada del servidor
                byte[] recibidos = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
                clientSocket.receive(paqueteRecibido);

                try (ByteArrayInputStream inputStream = new ByteArrayInputStream(paqueteRecibido.getData());
                     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                    dato = (Persona) objectInputStream.readObject();

                    System.out.println("Datos recibidos del servidor: " + dato);

                    clientSocket.close();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
