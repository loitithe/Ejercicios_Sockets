package Boletin2.Ejercicio5UDP;

import java.io.*;
import java.net.*;

public class UDPServer {
    private static Alumno[] alumnos = new Alumno[5];

    public static void main(String[] args) {
        inicializarAlumnos();

        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado. Esperando datagramas de clientes...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String idAlumno = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("ID del alumno recibido del cliente: " + idAlumno);

                Alumno alumno = buscarAlumno(idAlumno);
                enviarObjetoAlumno(alumno, receivePacket.getAddress(), receivePacket.getPort(), serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private static Alumno buscarAlumno(String idAlumno) {
        for (Alumno alumno : alumnos) {
            if (alumno.getIdAlumno().equals(idAlumno)) {
                return alumno;
            }
        }
        // Si el alumno no se encuentra, devolver un objeto Alumno vacío
        return new Alumno("", "", new Curso("", ""), 0);
    }

    private static void enviarObjetoAlumno(Alumno alumno, InetAddress address, int port, DatagramSocket serverSocket) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(alumno);

        byte[] sendData = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
    }
}