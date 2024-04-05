
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc;
    private static InetAddress dir = null;
    private static URL url;

    // REALIZA UN PROGRAMA EN JAVA QUE ADMITA DESDE CONSOLA NOMBRES DE MAQUINAS O DIRECCIONES IP Y VAYA MOSTRANDO POR PANTALLA INFORMACION SOBRE ELLAS, HACIENDO USO DE LA CLASE INETADRESS
    public static void ejercicio1() {
        try {
            dir = InetAddress.getByName(pedirString("Introduce nombre de maquina o ip"));
            pruebaMetodo(dir);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    //IMPLEMENTA UN PROGRAMA QUE RECOJA DE TECLADO UNA URL CON EL FORMATO http://www.sitioweb.dom Y ABRA UNA CONEXION A DICHO SITIO WEB, MOSTRANDO POR PANTALLA EL CODIGO HTML DE SU PAGINA INICIAL
    public static void ejercicio2() {
        try {
            String web = pedirString("Introduce la url");
            if (web.contains("https://www.") || web.contains("https://")) {
                url = new URL(web);

                //    contenidoHTML(url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader br;
        try (InputStream inputStream = url.openStream()) {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String input;
            while ((input = br.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //REALIZA UN PROGRAMA SERVIDOR TCP QUE ACEPTE DOS CLIENTES, MOSTRAR PARA CADA CLIENTE CONECTADO SUS PUERTOS LOCAL Y REMOTO. IMPLEMENTAR EL PROGRAMA CLIENTE , DONDE SE MUESTRE LOS PUERTOS LOCALES Y REMOTOS A LOS QUE SE ENCUENTRE CONECTADO, ASI COMO LA IP DE LA MAQUINA REMOTA A LA QUE SE CONECTA
    public static void ejercicio3(){
        TCPServidor servidor = new TCPServidor();
        TCPCliente cliente = new TCPCliente();
    }


    // muestra información de la URL u
    private static void infoWeb(URL url) {
        System.out.println(url.toString() + "\n" + url.getProtocol() + "\n" + url.getHost() + "\n" + url.getPort() + "\n" + url.getFile() + "\n" + url.getUserInfo() + "\n" + url.getPath() + "\n" + url.getAuthority() + "\n" + url.getQuery());
    }

    public static void pruebaMetodo(InetAddress dir) {
        System.out.println();
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("Metodo getLocalHost() : " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(dir.toString() + "\n" + dir.getHostAddress() + "\n" + dir.getHostName() + "\n" + dir.getCanonicalHostName());
    }



    public static void main(String[] args) {
        sc = new Scanner(System.in);
        //  ejercicio1();
        ejercicio2();

    }
    public static String pedirString(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = "";
            try {
                entrada = sc.next();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");

            }
            sc.nextLine();

            return entrada;
        }

    }
}