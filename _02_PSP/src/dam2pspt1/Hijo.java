package dam2pspt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hijo {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		ejecutarProcesoHijo();
		ejecutarProcesoHijo();
		ejecutarProcesoHijo();
		

	}
	
	private static double leerYSumar(String rutaArchivo) {
		double total = 0;
		try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea = "";
			while((linea = bf.readLine()) != null) {
				try {
					total += Double.parseDouble(linea.trim());//Sumo las lineas del archivo
				} catch (NumberFormatException e) {
					System.err.println("Error en la linea " + linea);
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de entrada");
		}
		return total;
	}

	private static void escribirResultado(String rutaSalida, Double total ) {
		String salida = "salida_ " +new File(rutaSalida).getName();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))){
			bw.write("Total: " + total);
			System.out.println("Resultado escrito de manera correcta en: " + salida);
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo de salida");
		}
		
	}
	
	private static void ejecutarProcesoHijo() {
		System.out.println("Introduce la ruta del fichero donde desea trabajar");
		String entrada = sc.nextLine();
		double total = leerYSumar(entrada);
		
		System.out.println("Introduce la ruta del archivo donde desea visualizar la salida");
		String salida = sc.nextLine();
		if (total != -1) {
			escribirResultado(salida,total);
		}
		System.out.println("Proceso hijo terminado de manera correcta");
	}

}
