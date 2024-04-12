package Boletin1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServidor {
    private static int numeroPuerto = 6000;

    //TODO
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            ServerSocket server = new ServerSocket(numeroPuerto);

            int numValido = pedirInt("Numero de clientes a conectar");
            System.out.println("Esperando..."); //Esperando conexión
            for (int i = 0; i < numValido; i++) {
                Socket clienteOn = server.accept();
                System.out.println("cliente conectado :\n"+clienteOn.getPort());

                //FLUJO DE SALIDA AL CLIENTE
                OutputStream aux = clienteOn.getOutputStream();
                DataOutputStream datOs= new DataOutputStream(aux);
                //envio de mensaje
             //   datOs.writeUTF("Conexion correcta al servidor "+server.toString());
                clienteOn.close();
                server.close();//Se finaliza la conexión con el cliente
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    public void getDatosServerSocket(ServerSocket serverSocket){
//
//        System.out.println(serverSocket.toString());
//    }
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
    public static int pedirInt(String mensaje) {
        Scanner sc= new Scanner(System.in);
        while (true) {
            System.out.println(mensaje);

            try {
                return sc.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }

        }

    }
}
/*    //  clienteOn = //Accept comienza el socket y espera una conexión desde un cliente
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

                entrada.close();
                flujoEntrada.close();
                salida.close();
                flujoSalida.close();*/