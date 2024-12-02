package dam2pspt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Hijo {

    public static void main(String[] args) {
        // Verificam si se ha proporcionado la ruta del archivo de entrada
        if (args.length < 1) {
            System.err.println("Error: No se proporcionó la ruta del archivo de entrada.");
            return; // Si no se proporciona la ruta, termina el proceso
        }

        String entrada = args[0]; // Obtiene la ruta del archivo de entrada desde los argumentos
        File archivoEntrada = new File(entrada); // Crea un objeto File con la ruta proporcionada

        // Verificamos si el archivo existe y es un archivo válido
        if (!archivoEntrada.exists() || !archivoEntrada.isFile()) {
            System.err.println("Error: El archivo \"" + entrada + "\" no existe o no es válido.");
            return; // Si el archivo no es válido, termina el proceso
        }

        System.out.println("Procesando archivo de entrada: " + entrada);

        double total;
        try {
            // Llamamos al método leerYSumar para procesar el archivo de entrada y obtener la suma
            total = leerYSumar(entrada);
        } catch (IOException e) {
            System.err.println("Error: No se pudo leer el archivo \"" + entrada + "\".");
            return; // Si ocurre un error leyendo el archivo, termina el proceso
        }

        // Genera el nombre del archivo de salida basado en el nombre del archivo de entrada
        String salida = generarNombreSalida(entrada);

        try {
            // Llamamos al método escribirResultado para guardar el resultado en un archivo de salida
            escribirResultado(salida, total);
            System.out.println("Proceso hijo terminado correctamente. Archivo de salida: " + salida);
        } catch (IOException e) {
            System.err.println("Error: No se pudo escribir en el archivo \"" + salida + "\".");
        }
    }

    // Método que lee el archivo y suma los números encontrados en cada línea
    private static double leerYSumar(String rutaArchivo) throws IOException {
        double total = 0;
        //BufferedReader para leer el archivo línea por línea
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                try {
                    // Intenta parsear cada línea a un número decimal y lo suma al total
                    total += Double.parseDouble(linea.trim());
                } catch (NumberFormatException e) {
                    // Si la línea no es un número válido, se ignora y se muestra una advertencia
                    System.err.println("Advertencia: La línea \"" + linea + "\" no es numérica y será ignorada.");
                }
            }
        }
        return total; 
    }

    // Método que escribe el resultado en el archivo de salida
    private static void escribirResultado(String rutaSalida, Double total) throws IOException {
        //BufferedWriter para escribir el resultado en el archivo de salida
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            bw.write("Total: " + total); // Escribe el resultado de la suma
            System.out.println("Resultado escrito correctamente en: " + rutaSalida);
        }
    }

    // Método que genera un nombre de archivo de salida basado en el nombre del archivo de entrada
    private static String generarNombreSalida(String rutaEntrada) {
        return "salida_" + new File(rutaEntrada).getName(); // Prefijo "salida_" + nombre del archivo original
    }
}
