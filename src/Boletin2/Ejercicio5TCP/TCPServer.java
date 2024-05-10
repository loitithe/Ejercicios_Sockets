package Boletin2.Ejercicio5TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static Alumno[] alumnos = new Alumno[5];

    /*Utilizando sockets TCP crea un programa servidor que inicialice un array de 5 objetos de tipo
    Alumno.
    El servidor, en un bucle infinito, solicitará al cliente un idAlumno y le devolverá el objeto
    Alumno que corresponda.
    Crea un programa cliente en el que se introduzca por teclado el idAlumno que se desea
    consultar. Dicho programa recogerá datos hasta recibir un * por parte del usuario.
    Si el idAlumno no se encuentra registrado, el servidor le devolverá un objeto Alumno con datos
    vacíos.*/
    public static void main(String[] args) {
        int puerto = 6000;

        for (int i = 0; i < alumnos.length; i++) {
            alumnos[i] = new Alumno("id" + i, "Alumno" + i, new Curso("curso" + i, "Curso basico"), 3);
        }
        try {
            ServerSocket server = new ServerSocket(puerto);
            System.out.println("esperando al cliente");
            Socket client = server.accept();
            ObjectOutputStream outObjeto = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inObjeto = new ObjectInputStream(client.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    private static Alumno getAlumnobyId(String idAlumno) {
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i].getIdalumno().equals(idAlumno)) {
                return alumnos[i];
            }
        }
        return null;
    }

    private static void inicializarAlumnos() {
        // Inicializar algunos alumnos para demostración
        Curso curso1 = new Curso("C001", "Matemáticas");
        Curso curso2 = new Curso("C002", "Historia");
        Curso curso3 = new Curso("C003", "Inglés");

        alumnos[0] = new Alumno("A001", "Juan", curso1, 80);
        alumnos[1] = new Alumno("A002", "María", curso2, 75);
        alumnos[2] = new Alumno("A003", "Pedro", curso3, 85);
        alumnos[3] = new Alumno("A004", "Laura", curso1, 90);
        alumnos[4] = new Alumno("A005", "Carlos", curso2, 70);
    }
}
