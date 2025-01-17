package red;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
Versión que acepta varias conexiones. Ejecutar desde varias ventanas cmd:
nc localhost 4444
Y escribir en ellas hasta que se escribe Adios
*/

public class P7_BasicServer {
    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(PORT);
            System.out.println("Servidor escuchando en el puerto: " + PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        while (true) {
            // Acepta una conexión de un cliente
            Socket socketCliente = socketServidor.accept();
            System.out.println("Cliente conectado: " + socketCliente);

            // Crea un nuevo hilo para manejar la comunicación con el cliente
            Thread clientThread = new Thread(() -> {
                try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true)
                ) {
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        System.out.println("Recibe Cliente: " + inputLine);
                        writer.println("Envia Servidor: " + inputLine);

                        // Si es "Adios" es que finaliza la comunicación
                        if (inputLine.equals("Adios")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                } finally {
                    try {
                        // Cierra el socket del cliente cuando termina la comunicación
                        socketCliente.close();
                    } catch (IOException e) {
                        System.out.println("Error al cerrar el socket del cliente: " + e.getMessage());
                    }
                }
            });

            // Inicia el hilo para manejar la comunicación con el cliente
            clientThread.start();
        }
    }
}
