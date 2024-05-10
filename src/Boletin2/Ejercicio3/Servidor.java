package Boletin2.Ejercicio3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(6000);
            System.out.println("Servidor UDP iniciado. Esperando conexiones...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Numeros dato = (Numeros) objectInputStream.readObject();
                int numero = dato.getNumero();
                long cuadrado = numero*numero;
                long cubo = cuadrado*numero;
                dato.setCubo(cubo);
                dato.setCuadrado(cuadrado);
                if (numero <= 0) {
                    System.out.println("Servidor finalizado.");
                    break;
                }
                try( ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                    objectOutputStream.writeObject(dato);

                    byte[] sendData = outputStream.toByteArray();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                }
            }

            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}