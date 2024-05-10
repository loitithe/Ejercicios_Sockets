package Boletin2.Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("PROGRAMA CLIENTE INICIADO....");
            InetAddress server = InetAddress.getLocalHost();
            int port = 6000;

            while (true) {
                System.out.print("Introduce un número (Escribe 0 o negativo para salir): ");
                int numero = scanner.nextInt();

                if (numero <= 0) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                Numeros dato = new Numeros();
                dato.setNumero(numero);
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                    objectOutputStream.writeObject(dato);
                    // envio de datos
                    byte[] enviados = outputStream.toByteArray();
                    DatagramPacket paqueteEnvio = new DatagramPacket(enviados, enviados.length, server, port);
                    clientSocket.send(paqueteEnvio);

                    //se recibe el objeto
                    byte[] recibidos = new byte[1024];
                    DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
                    clientSocket.receive(paqueteRecibido);

                    //convertimos bytes a objetos
                    try (ByteArrayInputStream inputStream = new ByteArrayInputStream(paqueteRecibido.getData());
                         ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                       dato= (Numeros) objectInputStream.readObject();

                        System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
                    }

                }


            }

            clientSocket.close();
        } catch (ConnectException ce) {
            System.out.println("ERROR AL ESTABLECER LA CONEXI�N CON EL SERVIDOR....");
            System.exit(0);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}