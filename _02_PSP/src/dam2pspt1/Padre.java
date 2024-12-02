package dam2pspt1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Padre {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Obtenemos el directorio donde se trabajará
        File directorio = obtenerDirectorio();
        
        // Listas para almacenar los procesos y los nombres de los archivos de salida
        List<Process> procesos = new ArrayList<>();
        List<String> salidas = new ArrayList<>();

        // Iniciamos procesos para cada archivo .txt en el directorio
        for (File file : listarArchivosTxt(directorio)) {
            String rutaAbsolutaEntrada = file.getAbsolutePath();
            
            // Configuramos el comando para ejecutar el proceso hijo (el programa Hijo)
            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-cp", "bin", "dam2pspt1.Hijo", rutaAbsolutaEntrada);
            pb.inheritIO(); // Inherir IO permite mostrar en la consola los mensajes de la aplicación hijo
            
            try {
                // Iniciamos el proceso para cada archivo .txt
                System.out.println("Iniciando proceso para: " + rutaAbsolutaEntrada);
                Process proceso = pb.start(); // Inicia el proceso hijo
                procesos.add(proceso); // Añade el proceso a la lista
                String nombreSalida = "salida_" + file.getName(); // Nombre del archivo de salida
                salidas.add(nombreSalida); // Añade el nombre del archivo de salida a la lista
            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso para el archivo: " + rutaAbsolutaEntrada);
            }
        }

        double sumaTotal = 0;

        // Esperamos que todos los procesos terminen y leemos los resultados de los archivos de salida
        for (int i = 0; i < procesos.size(); i++) {
            Process proceso = procesos.get(i); // Obtiene el proceso hijo
            String salida = salidas.get(i); // Obtiene el nombre del archivo de salida

            try {
                // Esperamos a que el proceso termine
                System.out.println("Esperando que el proceso termine: " + salida);
                proceso.waitFor(); // Espera a que el proceso hijo termine

                File of = new File(salida); // Crea un objeto File para el archivo de salida

                // Verifico si el archivo de salida existe
                if (!of.exists()) {
                    System.err.println("Error: El archivo de salida no existe: " + salida);
                    continue; // Si el archivo no existe, salta al siguiente archivo
                }

                // Lee el archivo de salida
                System.out.println("Leyendo archivo de salida: " + salida);
                try (BufferedReader bf = new BufferedReader(new FileReader(of))) {
                    String resultado = bf.readLine(); // Lee la primera línea del archivo de salida
                    if (resultado != null && resultado.startsWith("Total: ")) {
                        // Si la línea empieza con "Total: ", extrae el valor numérico
                        sumaTotal += Double.parseDouble(resultado.split(": ")[1]);
                    } else {
                        // Si el formato no es el esperado, muestra un mensaje de error
                        System.err.println("Error: Formato inesperado en el archivo de salida: " + salida);
                    }
                }
            } catch (InterruptedException e) {
                System.err.println("El proceso para el archivo " + salida + " fue interrumpido.");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo de salida: " + salida);
            }
        }

        // Muestra la suma total de todos los archivos procesados
        System.out.println("Suma total de todos los archivos: " + sumaTotal);
    }

    // Método que obtiene el directorio donde se trabajará
    public static File obtenerDirectorio() {
        File directorio;
        while (true) {
            // Se pide al usuario que ingrese la ruta del directorio
            System.out.println("Introduce la ruta del directorio donde desea trabajar:");
            String ruta = sc.nextLine();
            directorio = new File(ruta);

            // Verifica si la ruta corresponde a un directorio válido
            if (directorio.isDirectory() && directorio.listFiles() != null && directorio.listFiles().length > 0) {
                break; // Si es un directorio válido con archivos, sale del bucle
            }
            // Si no es válido, se le pide al usuario que lo intente nuevamente
            System.out.println("Directorio no válido o vacío. Intente nuevamente.");
        }
        return directorio; // Devuelve el directorio
    }

    // Método que lista todos los archivos .txt en el directorio proporcionado
    public static File[] listarArchivosTxt(File directorio) {
        // Filtramos y devolvemos solo los archivos que terminan en ".txt"
        return directorio.listFiles((dir, name) -> name.endsWith(".txt"));
    }
}
