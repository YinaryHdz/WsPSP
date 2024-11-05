package _01_Procesos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EjercicioEjecucionComandos {

	public static void main(String[] args) {
		/*
		 * Realizar un programa que ejecute varios comandos (notepad, calc…) uno detrás de otro, 
		 * de forma secuencial y haz que tu programa obtenga el código de finalización de cada uno 
		 * de ellos. Para cada programa imprime el nombre y su código de finalización.
		 * 
		 * Prueba a poner aplicaciones que no existan o comandos con parámetros incorrectos.
		 * ¿Qué hace el IDE si pones System.exit(10) al final del código para acabar tu programa?. 
		 * Fíjate en la consola. ¿Qué hace el IDE si pones System.exit(0) para acabar tu programa?. 
		 * ¿Cuál es entonces el valor por defecto?
		 */
		
		// Lista de comandos a ejecutar
        List<String> comandosWindows = Arrays.asList("notepad", "calc", "cmd /c dir", "comando_invalido");
        List<String> comandosLinux = Arrays.asList("gedit", "gnome-calculator", "ls", "comando_invalido");

        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        List<String> comandos = sistemaOperativo.contains("win") ? comandosWindows : comandosLinux;

        for (String comando : comandos) {
            try {
                System.out.println("Ejecutando comando: " + comando);
                ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
                Process proceso = pb.start();
                int codigoSalida = proceso.waitFor();
                System.out.println("Comando '" + comando + "' finalizado con código de salida: " + codigoSalida);

            } catch (IOException e) {
                System.out.println("Error al intentar ejecutar el comando '" + comando + "': " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("El comando '" + comando + "' fue interrumpido: " + e.getMessage());
            }
        }

        //Comportamiento con System.exit()
        System.out.println("Fin del programa");
        System.exit(10);  

	}

}
