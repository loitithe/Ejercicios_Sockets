package Boletin2.Ejercicio6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private static List<Socket> listClientSocket;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Crea el ServerSocket en el puerto especificado
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones en el puerto 12345...");
            listClientSocket = new ArrayList<>();
            while (true) {
                // Espera a que un cliente se conecte

                Socket clientSocket = serverSocket.accept();
                listClientSocket.add(clientSocket);
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());

                // Crea un nuevo hilo para manejar la conexión con este cliente
                Client clientHandler = new Client(clientSocket, listClientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierra el ServerSocket
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Client extends Thread {
    private Socket clientSocket;
    private List<Socket> lista;

    public Client(Socket clientSocket, List<Socket> listClientSocket) {
        this.clientSocket = clientSocket;
        this.lista = listClientSocket;
    }

    @Override
    public void run() {
        try {
            // Crea flujos de entrada y salida para comunicarse con el cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Lee mensajes del cliente y responde
            String message;
            while ((message = in.readLine()) != null) {
                for (Socket o : lista) {
                    System.out.println("Mensaje recibido: " + message.toUpperCase());
                    out = new PrintWriter(o.getOutputStream(), true);
                    out.println("Mensaje recibido: " + message.toUpperCase());
                }
            }

            // Cierra los flujos y el socket del cliente
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
