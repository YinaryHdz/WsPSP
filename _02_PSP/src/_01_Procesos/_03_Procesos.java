package _01_Procesos;

import java.io.IOException;

public class _03_Procesos {

	public static void main(String[] args) {
		try {
            // Comando para abrir Notepad
            String comando1 = "notepad.exe";
            ProcessBuilder pb1 = new ProcessBuilder(comando1);
            // Iniciar el proceso para abrir Notepad
            Process proceso1 = pb1.start();

            // Comando para abrir un archivo específico en Notepad
            String[] comando2 = { "notepad.exe", "C:\\dir1\\f5.txt" };
            ProcessBuilder pb2 = new ProcessBuilder(comando2);
            pb2.start();
            
            String comando3 = "notepad.exe C:\\dir1\\f5.txt";
            ProcessBuilder pb3 = new ProcessBuilder(comando3);
            pb3.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
