package red.ejercicioServidorJuego;

import red.p8.Worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PUERTO = 2000;

    public static void main(String[] args) {

        try ( ServerSocket socketServidor = new ServerSocket(PUERTO)){
            Socket socketCliente = null;
            while(true){
                socketCliente = socketServidor.accept();
                new Worker(socketCliente).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
