package _01_Procesos;

import java.io.*;
import java.util.Scanner;

public class Repaso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo de entrada:");
        String entrada = sc.nextLine();

        // Leer archivo y calcular el total
        double total = leerArchivoYSumar(entrada);
        
        if (total != -1) {
            // Escribir el resultado en un archivo de salida
            escribirResultado(entrada, total);
        }

        System.out.println("Proceso del hijo terminado correctamente.");
        sc.close();
    }

    // Función para leer el archivo de entrada y calcular la suma total
    private static double leerArchivoYSumar(String rutaEntrada) {
        double total = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaEntrada))) {
            String linea = "";
            while ((linea = bf.readLine()) != null) {
                try {
                    total += Double.parseDouble(linea.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Linea no valida: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontre el archivo de entrada.");
            return -1;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de entrada.");
            return -1;
        }
        return total;
    }

    // Función para escribir el resultado en un archivo de salida
    private static void escribirResultado(String rutaEntrada, double total) {
        String salida = "output_" + new File(rutaEntrada).getName();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {
            bw.write("Total: " + total);
            System.out.println("Resultado escrito en el archivo: " + salida);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de salida.");
        }
    }
}
