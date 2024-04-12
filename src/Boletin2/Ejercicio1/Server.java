package Boletin2.Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /**
     * Un profesor podrá impartir hasta 3 asignaturas. // comprobacion del set asignaturas
     * Utilizando sockets TCP, implementar un programa servidor que inicialice un array de 5 objetos
     * de tipo Profesor. El servidor aceptará conexiones de clientes en un bucle infinito. Cada vez
     * que se conecte un cliente, el servidor le asignará un id, que empezará en 1 e irá
     * incrementándose cada vez que se conecta un nuevo cliente. El servidor recibirá del cliente un
     * idProfesor y le devolverá el objeto Profesor asociado.
     * Crea un programa cliente en el que se introduzca por teclado el idProfesor que se desea
     * consultar. Dicho programa recogerá datos hasta recibir un * por parte del usuario.
     * Si el idProfesor no se encuentra registrado, el servidor le devolverá un objeto Profesor con
     * datos vacíos
     */
    public static void main(String[] args) {
        Profesor[] profesores = new Profesor[5];

        boolean existeProfesor= false;
        for (int i = 0; i < profesores.length; i++) {
            profesores[i] = new Profesor();
            profesores[i].setIdProfesor(i);
            profesores[i].setNombre("Profesor" + i);

        }
        try {
            ServerSocket servidor = new ServerSocket(6000);
            Cliente cliente;

            String cadena = "";
            InputStream entrada = null;
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            OutputStream salida = null;
            int i = 1;
            while (true) {
                cliente = (Cliente) servidor.accept(); //Accept
                ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream());// comienza el socket y espera una conexión desde un cliente
                cliente.setId(i++);
                salida = cliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(salida);

                while (!cadena.equals("*")) {
                    entrada = cliente.getInputStream();
                    flujoEntrada = new DataInputStream(entrada);
                    cadena = flujoEntrada.readUTF();
                    try {
                        int caracteres = Integer.parseInt(cadena);
                        for (int j = 0; j < profesores.length; j++) {
                            if (profesores[j].getIdProfesor() == caracteres) {
                               existeProfesor=true;
                           }else{
                                existeProfesor=false;
                            }

                        }

                        if (existeProfesor) {
                            outObject.writeObject(profesores[i-1]);
                            System.out.println(profesores[i-1].toString() + " enviado al cliente");
                        }else{
                            outObject.writeObject(new Profesor());
                        }
                        flujoSalida.writeInt(caracteres);
                    } catch (NumberFormatException e) {
                    }


                }
                // ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream());


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
