package Boletin2.Ejercicio6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor TCP iniciado. Esperando clientes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde la dirección IP " + clientSocket.getInetAddress().getHostAddress() + " y puerto remoto " + clientSocket.getPort());

                // Crear un nuevo hilo para atender al cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("Mensaje recibido del cliente: " + inputLine);
                    if (inputLine.equals("*")) {
                        break;
                    }
                    String response = inputLine.toUpperCase();
                    writer.write(response + "\n");
                    writer.flush();
                }

                System.out.println("Cliente desconectado desde la dirección IP " + clientSocket.getInetAddress().getHostAddress() + " y puerto remoto " + clientSocket.getPort());
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}