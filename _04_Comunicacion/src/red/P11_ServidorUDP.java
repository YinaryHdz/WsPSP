package red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class P11_ServidorUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte []enviados = new byte[1824];
        byte [] recibido = new byte[1824];
        String cadena;

        while(true){
            System.out.println("Esperando respuesta");
            DatagramPacket paqRecibido = new DatagramPacket(recibido, recibido.length);
            serverSocket.receive(paqRecibido);
            cadena = new String(paqRecibido.getData());
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();
            System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
            System.out.println("\tMensaje recibido: " + cadena.trim());

            String mayuscula = cadena.trim().toUpperCase();
            enviados = mayuscula.getBytes();
            DatagramPacket paqEnviado = new DatagramPacket(enviados,enviados.length, IPOrigen,puerto);
            serverSocket.send(paqEnviado);
            if (cadena.trim().equals("")){
                break;
            }
        }
        serverSocket.close();
        System.out.println("Socket cerrado");
    }
}
