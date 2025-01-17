package red.p8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultihilo {

    public static final int PUERTO = 5555;

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
