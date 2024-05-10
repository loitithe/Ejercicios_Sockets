package MulticastSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ClientMultiClient {
    public static void main(String[] args) throws IOException {
        int port = 6000;

        try {
            Socket client = new Socket("localhost",port);
            PrintWriter fsalida = new PrintWriter(client.getOutputStream(),true);
            BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String cadena, eco="";
            System.out.println("Introduce una cadena : ");
            cadena = in.readLine();
            while (cadena!=null){
                fsalida.println(cadena);
                eco = fentrada.readLine();
                System.out.println("ECO : "+eco);
                System.out.println("Mensaje : ");
                cadena= in.readLine();
            }
            fsalida.close();
            fentrada.close();
            System.out.println("Fin envio");
            in.close();
            client.close();
        }catch (IOException e){e.printStackTrace();}
    }
}
