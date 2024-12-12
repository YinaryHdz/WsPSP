package red;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class P2InterfaceAdresses {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface nif = networkInterfaces.nextElement();
            List<InterfaceAddress> list = nif.getInterfaceAddresses();
            for (InterfaceAddress iaddr : list){
                System.out.println("Interface name: " + nif.getName());
                System.out.println("getAdress:" + iaddr.getAddress());
                System.out.println("getBroadcast: " + iaddr.getBroadcast());
                System.out.println("PrefixLenght: " + iaddr.getNetworkPrefixLength());
                System.out.println("HashCode: " + iaddr.hashCode());
                System.out.println("toString: " + iaddr.toString());
                System.out.println("--------");
            }

        }
    }
}
