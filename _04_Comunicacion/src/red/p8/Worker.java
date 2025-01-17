package red.p8;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread{
    private Socket socketCliente;
    private BufferedReader entrada = null;
    private PrintWriter salida = null;

    public Worker(Socket socketCliente){
        this.socketCliente = socketCliente;
    }
    public void run(){
        while(true){
            String mensajeRecibido = "";
            try {
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
                mensajeRecibido = entrada.readLine();
                if(mensajeRecibido.trim().equalsIgnoreCase("")){
                    break;
                }
                System.out.println("<-- Cliente: " + mensajeRecibido);
                salida.println("Mensaje de servidor a cliente ");
                System.out.println("--> Cliente: mensaje de servidor a cliente");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Cliente cerró la conección");
        try {
            entrada.close();
            salida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
