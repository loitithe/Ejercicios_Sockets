package Boletin2.Ejercicio1;

public class Especialidad {
    int id ;
    String nombreEsp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    public Especialidad(int id, String nombreEsp) {
        this.id = id;
        this.nombreEsp = nombreEsp;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombreEsp='" + nombreEsp + '\'' +
                '}';
    }
}
