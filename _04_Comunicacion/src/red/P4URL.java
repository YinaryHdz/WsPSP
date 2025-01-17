package red;

import java.net.MalformedURLException;
import java.net.URL;

public class P4URL {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://tetr.io/");
        System.out.println("Protocolo: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        System.out.println("Puerto: " + url.getPort());
        System.out.println("Puerto por defecto:" + url.getDefaultPort());
        System.out.println("Referencia: " + url.getRef());
        System.out.println("Query: " + url.getQuery());
        System.out.println("Path: " + url.getPath());
        System.out.println("File: " + url.getFile()  );

    }
}
