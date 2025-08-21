import java.io.*;
import java.net.*;

public class servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            // Espera una conexión de un cliente
            Socket socketCliente = servidor.accept();
            System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());

            // Flujo de salida: enviar mensaje al cliente
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
            out.println("¡Hola desde el servidor!");

            // Flujo de entrada: recibir mensaje del cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            String mensajeCliente = in.readLine();
            System.out.println("Mensaje del cliente: " + mensajeCliente);

            // Cerrar recursos
            socketCliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
