package red;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P3InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address1 = InetAddress.getLocalHost();
        System.out.println("InetAddress del LocalHost" +address1);

        InetAddress address2 = InetAddress.getByName("servidorDisco");
        System.out.println("InetAddress del servidorDisco" +address2);
        System.out.println("Nombre del host: " + address2.getHostName());

        InetAddress address3 [] = InetAddress.getAllByName("www.youtube.com");
        System.out.println("Direcciones IP de www.youtube.com ");
        for (int i = 0; i < address3.length; i++){
            System.out.println("\t" + address3[i]);
        }
        byte [] IPAddress = {125, 0,0,1};
        InetAddress address4 = InetAddress.getByAddress(IPAddress);
        System.out.println("InetAddress de 125,0,0,1"+ address4);

        byte [] IPAddress2 = {105, 22, (byte)223, (byte)186};
        InetAddress address5 = InetAddress.getByAddress("gfg.com",IPAddress2);
        System.out.println("InetAddress de 105, 22, (byte)223, (byte)186"+ address5);


    }
}
