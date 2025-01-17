package red;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class P6_ServidorTCP {
    public static final int PORT = 4444;
    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(PORT);
            System.out.println("Servidor escuchando por el puerto: " + PORT);
        } catch (IOException e) {
            System.out.println("No se puede escuchar por el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = socketServidor.accept();
        BufferedReader entrada = null;
        PrintWriter salida = null;

        System.out.println("Conexion establecida: " + socketCliente);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())));

        while(true){
            String str = entrada.readLine();
            System.out.println("Cliente: " + str);
            salida.println("Servidor: " + str);
            if(str.equals("Adios")){
                break;
            }
        }

        socketServidor.close();
        socketCliente.close();
        entrada.close();
        salida.close();
    }
}
