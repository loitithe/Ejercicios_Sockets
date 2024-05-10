package MulticastSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
    private BufferedReader fentrada;
    private PrintWriter fsalida;
    private Socket socket;

    public HiloServidor(Socket cliente) {
        this.socket = cliente;
        try {
            fsalida = new PrintWriter(socket.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String cadena = "";
        try {
            while (!cadena.trim().equals("*")) {
                System.out.println("Comunicando con "+socket.toString());
                cadena= fentrada.readLine();
                fsalida.println(cadena.trim().toUpperCase());
            }
            System.out.println("Terminando comunicacion con "+socket.toString());
            fsalida.close();
            fentrada.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
