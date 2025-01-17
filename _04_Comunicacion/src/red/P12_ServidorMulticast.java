package red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class P12_ServidorMulticast {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        MulticastSocket ms = new MulticastSocket();
        int puerto = 12345;
        InetAddress grupoMulticast = InetAddress.getByName("225.0.0.1");
        String cadena = "x";
        while(!cadena.trim().equals("")){
            System.out.println("Datos a enviar al grupo: ");
            cadena = sc.nextLine();
            DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupoMulticast, puerto);
            ms.send(paquete);
        }
        ms.close();
        System.out.println("Socket cerrado... ");
    }

}
