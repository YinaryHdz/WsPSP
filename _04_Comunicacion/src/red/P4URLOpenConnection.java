package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class P4URLOpenConnection {
    public static void main(String[] args) throws IOException {
        URL u = new URL("https://www.bbc.com");
        URLConnection urlConnect = u.openConnection();

        System.out.println("getDate: " + urlConnect.getDate());
        System.out.println("getContentType: " + urlConnect.getContentType());
        System.out.println("getHeaderField(2): " + urlConnect.getHeaderField(2));

        InputStream stream = urlConnect.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String linea;
        while ((linea = in.readLine()) != null){
            System.out.println(linea);
        }
    }
}
