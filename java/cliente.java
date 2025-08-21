import java.io.*;
import java.net.*;

public class cliente {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // localhost
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujo de entrada: leer mensaje del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensajeServidor = in.readLine();
            System.out.println("Mensaje del servidor: " + mensajeServidor);

            // Flujo de salida: enviar mensaje al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("¡Hola servidor!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
