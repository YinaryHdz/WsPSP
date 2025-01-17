package red.ejercicioServidorJuego;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Worker extends Thread {
    private Socket socketCliente;
    private BufferedReader entrada = null;
    private PrintWriter salida = null;
    private int numeroAleatorio;

    Worker(Socket socketCliente) {
        this.socketCliente = socketCliente;
        // Generar el número aleatorio entre 0 y 100 al iniciar el Worker
        Random random = new Random();
        this.numeroAleatorio = random.nextInt(101);
        System.out.println("Número aleatorio generado para el cliente: " + numeroAleatorio);
    }

    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
            salida.println("Bienvenido al juego de adivinar el número. ¡Envía un número entre 0 y 100!");

            while (true) {
                String mensajeRecibido = entrada.readLine();
                if (mensajeRecibido == null || mensajeRecibido.trim().equalsIgnoreCase("")) {
                    break;
                }

                System.out.println("<-- Cliente: " + mensajeRecibido);

                try {
                    int intento = Integer.parseInt(mensajeRecibido.trim());
                    if (intento < 0 || intento > 100) {
                        salida.println("Por favor, ingresa un número válido entre 0 y 100.");
                    } else if (intento < numeroAleatorio) {
                        salida.println("El número es mayor.");
                    } else if (intento > numeroAleatorio) {
                        salida.println("El número es menor.");
                    } else {
                        salida.println("¡Correcto! Has adivinado el número.");
                        System.out.println("Cliente ha adivinado el número correctamente.");
                        break;
                    }
                } catch (NumberFormatException e) {
                    salida.println("Por favor, ingresa un número válido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            System.out.println("Cliente cerró la conexión");
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socketCliente != null) socketCliente.close();
            } catch (IOException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }
}
