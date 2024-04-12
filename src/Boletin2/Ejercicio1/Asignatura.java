package Boletin2.Ejercicio1;

public class Asignatura {
    int id;
    String nombreAsig;

    public String getNombreAsig() {
        return nombreAsig;
    }

    public void setNombreAsig(String nombreAsig) {
        this.nombreAsig = nombreAsig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Asignatura(int id, String nombreAsig) {
        this.id = id;
        this.nombreAsig = nombreAsig;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombreAsig='" + nombreAsig + '\'' +
                '}';
    }
}
