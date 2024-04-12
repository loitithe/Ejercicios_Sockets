package Boletin2.Ejercicio1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Socket {
 //   private static int numeroPuerto = 6000;
    int id;

    public Cliente(int id) {
        this.id = id;

    }
    public Cliente(){}
    public void setId(int id) {
        this.id = id;
    }

    private void buscarProfesor(){
        try {
            DataInputStream flujoEntrada = new DataInputStream(this.getInputStream());
            pedirString(this);
            this.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //
    public  void pedirString(Socket clientOn) {

        Scanner sc = new Scanner(System.in);
        String mensaje = "";
        while (!mensaje.equals("*")) {
            try {
                OutputStream salida = null;
                salida = clientOn.getOutputStream();
                DataOutputStream daOs = new DataOutputStream(salida);
                daOs.writeUTF(mensaje);
                System.out.println("numero de caracteres");
                System.out.println("Introduce cadena");
                mensaje = sc.nextLine();

                ObjectInputStream profesor = new ObjectInputStream(this.getInputStream());
                System.out.println(profesor.toString()+" ha llegado al cliente");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }

        }

    }

    public static void main(String[] args) {
        Cliente cliente= new Cliente();
        cliente.buscarProfesor();
    }
}
