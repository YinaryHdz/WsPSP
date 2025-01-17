package red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class P12_ClienteMulticast2 {
    public static void main(String[] args) throws IOException {
        int puerto = 12345;
        MulticastSocket ms = new MulticastSocket(puerto);
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        ms.joinGroup(grupo);
        String msg = "x";
        while(!msg.trim().equals("")){
            byte[]buf = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);
            msg = new String(paquete.getData());
            System.out.println("recibido: " + msg.trim());
        }
        ms.leaveGroup(grupo);
        ms.close();
        System.out.println("Socket cerrado...");
    }
}
