package Boletin2.Ejercicio6;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("Conexión establecida con el servidor.");

            String userInput;
            do {
                System.out.print("Introduce un mensaje ( * para salir): ");
                userInput = reader.readLine();
                out.write(userInput + "\n");
                out.flush();

                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);
            } while (!userInput.equals("*"));

            socket.close();
            System.out.println("Cliente finalizado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}