package red;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class P5_ClienteTCP {

    public static void main(String[] args) throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        socketCliente = new Socket("localhost", 4444);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        salida = new PrintWriter(socketCliente.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);
        String linea;

        while (true){
            System.out.println("Mensaje: ");
            linea = sc.nextLine();
            salida.println(linea);
            linea = entrada.readLine();
            System.out.println("Respuesta servidor: " + linea);
            if(linea.equals("Adios")){
                break;
            }
        }

        salida.close();
        entrada.close();
        sc.close();
        socketCliente.close();
    }


}
