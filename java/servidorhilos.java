import java.io.*;
import java.net.*;

// Clase que maneja a cada cliente en un hilo separado
class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado desde " + socket.getInetAddress());

            // Flujo de entrada (leer del cliente)
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            // Flujo de salida (escribir al cliente)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Enviar saludo inicial
            out.println("¡Hola desde el servidor con hilos!");

            // Leer mensaje del cliente
            String mensajeCliente = in.readLine();
            if (mensajeCliente != null) {
                System.out.println("Mensaje del cliente: " + mensajeCliente);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Clase principal del servidor con hilos
public class servidorhilos {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor (multi-cliente) escuchando en el puerto " + puerto + "...");

            // Aceptar conexiones en bucle infinito
            while (true) {
                Socket socketCliente = servidor.accept();

                // Crear un hilo para cada cliente
                ManejadorCliente manejador = new ManejadorCliente(socketCliente);
                Thread hilo = new Thread(manejador);
                hilo.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
