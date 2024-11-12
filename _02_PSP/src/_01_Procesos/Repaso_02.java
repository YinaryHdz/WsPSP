package _01_Procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repaso_02 {

	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		File directorio =  obtenerDirectorio();
		
		//Lista de los procesos y las salidas
		List<Process> procesos = new ArrayList<>();
        List<String> salidas = new ArrayList<>();
        
      // Iniciar procesos hijos para cada archivo .txt
        for (File file : listarArchivosTxt(directorio)) {
        	ProcessBuilder pb = new ProcessBuilder("java", "ProcesoHijo", file.getAbsolutePath());
        	try {
				Process proceso = pb.start();
				procesos.add(proceso);
				
				String salida = "output_" + file.getName();
                salidas.add(salida);
			} catch (IOException e) {
				System.err.println("Error al iniciar el proceso para el archivo: " + file.getName());
                e.printStackTrace();
			}
        	
        }
        
        double sumaTotal = 0;
        
     // Esperar a que cada proceso hijo termine y leer los resultados      
        for (int i = 0; i < procesos.size(); i++) {
        	 Process proceso = procesos.get(i);
             String salida = salidas.get(i);
             try {
				proceso.waitFor();// Esperar a que el proceso hijo termine
				File of = new File(salida);
	             if (of.exists()) {
	            	 try (BufferedReader leer = new BufferedReader(new FileReader(of))) {
	                     String resultado = leer.readLine();
	                     if (resultado != null) {
	                         sumaTotal += Double.parseDouble(resultado.split(": ")[1]);
	                     }
	                 } catch (IOException e) {
	                         System.err.println("Error al leer el archivo de salida: " + salida);
	                         e.printStackTrace();
	                     }
	             } else {
	                    System.err.println("Archivo de salida no encontrado: " + salida);
	                }
             } catch (InterruptedException e) {
                 System.err.println("Proceso interrumpido para el archivo: " + salida);
                 e.printStackTrace();
             }
         }
        System.out.println("Suma total de todos los archivos: " + sumaTotal);
	}
	
	private static File obtenerDirectorio() {
		File directorio;
        while (true) {
            System.out.print("Introduce la ruta del directorio de entrada: ");
            String ruta = sc.nextLine();
            directorio = new File(ruta);
          //Con el siguiente flujo controlo lo siguiente:
			/*
			 * 1. Si el directorio introducido es directorio
			 * 2. Si contiene archivos dentro
			 */
            if (directorio.isDirectory() && directorio.listFiles() != null && directorio.listFiles().length > 0) {
            	//Si se cumple la condicion salimos del bucle
                break;
            }
            System.out.println("Directorio no valido o vacio");
        }
        return directorio;
	}

	public static File[] listarArchivosTxt(File directorio) {
		//Con FilenameFilter se filtran los archivos que terminan en .txt
		return  directorio.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
		
	}

}
