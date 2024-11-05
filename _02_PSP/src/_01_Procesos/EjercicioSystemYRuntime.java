package _01_Procesos;

import java.util.Map;
import java.util.Properties;

public class EjercicioSystemYRuntime {

	public static void main(String[] args) {
		/*
		 * Usando métodos de las clases System y Runtime hacer un programa que muestre 
		 * todas las propiedades establecidas en el sistema operativo y sus valores: 
		 * memoria total, memoria libre, memoria en uso y los procesadores disponibles
		 * Mira los métodos que proporcionan las clases Runtime y System. Intenta obtener una lista 
		 * u otra estructura de datos que te permita recorrer las propiedades para ir mostrando sus nombres y valores.
		 */
		
		// Obtener y mostrar propiedades del sistema
        System.out.println("Propiedades del sistema:");
        Properties propiedades = System.getProperties();
        for (Map.Entry<Object, Object> entry : propiedades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== Información de memoria y procesadores ===\n");
        
        Runtime runtime = Runtime.getRuntime();

        // Memoria total disponible para la JVM
        long memoriaTotal = runtime.totalMemory();
        System.out.println("Memoria total (bytes): " + memoriaTotal);
        System.out.println("Memoria total (MB): " + memoriaTotal / (1024 * 1024) + " MB");

        // Memoria libre en la JVM
        long memoriaLibre = runtime.freeMemory();
        System.out.println("Memoria libre (bytes): " + memoriaLibre);
        System.out.println("Memoria libre (MB): " + memoriaLibre / (1024 * 1024) + " MB");

        // Memoria en uso por la JVM
        long memoriaEnUso = memoriaTotal - memoriaLibre;
        System.out.println("Memoria en uso (bytes): " + memoriaEnUso);
        System.out.println("Memoria en uso (MB): " + memoriaEnUso / (1024 * 1024) + " MB");

        // Procesadores disponibles
        int procesadores = runtime.availableProcessors();
        System.out.println("Procesadores disponibles: " + procesadores);

	}

}
