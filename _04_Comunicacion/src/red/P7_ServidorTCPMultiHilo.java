package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class P7_ServidorTCPMultiHilo {
    public static final int PORT = 444;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;

        try {
            socketServidor = new ServerSocket(PORT);
            System.out.println("Servidor escuchando por el puerto: " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se puede escuchar por el puerto: " + PORT);
            System.exit(-1);
        }

        while (true) {
            try {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Conexión establecida: " + socketCliente);

                Thread clienteThread = new Thread(() -> {
                    try (
                            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)
                    ) {
                        String mensaje;
                        while ((mensaje = entrada.readLine()) != null) {
                            System.out.println("Cliente: " + mensaje);
                            salida.println("Servidor: " + mensaje);

                            if (mensaje.equalsIgnoreCase("adios")) {
                                System.out.println("Cerrando conexión con cliente: " + socketCliente);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                    } finally {
                        try {
                            socketCliente.close();
                        } catch (IOException e) {
                            System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
                        }
                    }
                });

                // Iniciar el hilo
                clienteThread.start();

            } catch (IOException e) {
                System.err.println("Error al aceptar conexión: " + e.getMessage());
            }
        }
    }
}