package Boletin2.Ejercicio1;

import java.io.Serializable;
import java.util.Arrays;

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
public class Profesor implements Serializable {
    int idProfesor;
    String nombre;
    Asignatura[] asignaturas;
    Especialidad esp;

    public Profesor(int idProfesor, String nombre, Asignatura[] asignaturas, Especialidad esp) {

        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.esp = esp;
    }

    public Profesor() {

    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        if (asignaturas.length <= 2)
            this.asignaturas = asignaturas;
    }

    public Especialidad getEsp() {
        return esp;
    }

    public void setEsp(Especialidad esp) {
        this.esp = esp;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", nombre='" + nombre + '\'' +
                ", asignaturas=" + Arrays.toString(asignaturas) +
                ", esp=" + esp +
                '}';
    }
}
