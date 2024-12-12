package red;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class P1NetworkInterface {
    public static void main(String[] args) {
        ArrayList<NetworkInterface> interfaces;

        {
            try {
                interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                System.out.println("Información sobre interfaces de red existentes: "+interfaces);
                for(NetworkInterface iface : interfaces){
                    if(iface.isUp()){
                        System.out.println("Nombre de interfaz: "+iface.getName());
                        System.out.println("Display name interfaz: "+iface.getDisplayName());
                        System.out.println("Dirección hardware: "  + Arrays.toString(iface.getHardwareAddress()));
                        System.out.println("Parent: "+iface.getParent());
                        System.out.println("Index: "+iface.getIndex());
                        System.out.println("Interface Adresses: ");
                        for(InterfaceAddress addr: iface.getInterfaceAddresses()){
                            System.out.println("\t\t"+ addr.getAddress().toString());
                        }
                        System.out.println("InetAdresses: ");
                        Enumeration<InetAddress> en = iface.getInetAddresses();
                        while(en.hasMoreElements()){
                            System.out.println("\t\t"+en.nextElement().toString());
                        }
                        System.out.println("\tSubinterfaces: "+ Collections.list(iface.getSubInterfaces()));
                        System.out.println("\tisLoopback:" + iface.isLoopback());
                        System.out.println("\tisVirtual" + iface.isVirtual());
                        System.out.println("\tisPointToPoint" + iface.isPointToPoint());
                        System.out.println("Soporta multicast:" + iface.supportsMulticast());

                    }else{
                        System.out.println("Interfaz no activa: "+iface.getName());
                    }
                }
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
