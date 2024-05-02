package Boletin2.Ejercicio3;

import java.io.Serializable;

public class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    // Constructor sin parámetros
    public Numeros() {}

    // Constructor con parámetros
    public Numeros(int numero) {
        this.numero = numero;
        this.cuadrado = (long) numero * numero;
        this.cubo = (long) numero * numero * numero;
    }

    // Métodos get y set
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        this.cuadrado = (long) numero * numero;
        this.cubo = (long) numero * numero * numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    @Override
    public String toString() {
        return "Numero: " + numero + ", Cuadrado: " + cuadrado + ", Cubo: " + cubo;
    }
}