package Boletin2.Ejercicio4UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(6000);
            System.out.println("Servidor UDP iniciado. Esperando datagramas de clientes...");

            while (true) {
                byte[] recibidos = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
                serverSocket.receive(paqueteRecibido);
                try (ByteArrayInputStream inputStream = new ByteArrayInputStream(paqueteRecibido.getData());
                     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                    Persona dato = (Persona) objectInputStream.readObject();
                    // Modificar los datos de la persona
                    dato.setEdad(dato.getEdad() + 1);
                    dato.setNombre(dato.getNombre() + " modificado");

                    System.out.println("Datos recibidos del cliente: " + dato);

                    // Enviar el objeto Persona modificado de vuelta al cliente
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                         ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                        objectOutputStream.writeObject(dato);
                        byte[] sendData = outputStream.toByteArray();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                        serverSocket.send(sendPacket);
                    }
                    System.out.println("Datos enviados al cliente: " + dato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
