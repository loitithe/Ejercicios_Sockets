import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServidor {
    private static int numeroPuerto = 6000;

    //TODO
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(numeroPuerto);
            System.out.println("Esperando..."); //Esperando conexión
            Socket clienteOn = null;
            clienteOn = server.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");
            //flujo entrada cliente
            InputStream entrada = null;
            entrada = clienteOn.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            //cliente envia mensaje
            System.out.println("cliente " + flujoEntrada.readUTF());

            OutputStream salida = null;
            salida = clienteOn.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            //envia mensaje al cliente
            flujoSalida.writeUTF("servidor respondiendo");

//            while (true) {
//                clienteOn = server.accept();
//
//            }
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            clienteOn.close();
            server.close();//Se finaliza la conexión con el cliente
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*   try {
            ServerSocket server = new ServerSocket(numeroPuerto);
           ArrayList <Socket> clienteOn = null;
           ArrayList<InputStream>input= null;
           ArrayList<DataInputStream>flujo_input=null;
            System.out.println("Esperando al cliente ... ");
            for (Socket socket : clienteOn) {
               socket = server.accept();
               input.add(socket.getInputStream());
               flujo_input= new DataInputStream(input.);
            }

            //flujo de entrada del cliente
        //    InputStream input = null;



        } catch (IOException e) {
            e.printStackTrace();
        }*/
}
