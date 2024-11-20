package dam2pspt1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Padre {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		File directorio = obtenerDirectorio();
		//Lista de los procesos y las salidas
		List<Process> procesos = new ArrayList<>();
		List<String> salidas = new ArrayList<>();
		
		for(File file : listarArchivosTxt(directorio)) {//Recorro el directorio filtrando solo los txt
			ProcessBuilder pb = new ProcessBuilder("java","Hijo", file.getAbsolutePath());//Accedo a la ruta absoluta para obtener el hijo
			pb.inheritIO();
			try {
				Process proceso = pb.start();//Arrancar el proceso
				procesos.add(proceso);//Añado este proceso a mi lista de procesos
				String salida = "salida_" + file.getName();
				salidas.add(salida);
			} catch (IOException e) {
				System.err.println("Error al iniciar el proceso" + file.getName());
			}
			
		}
		
		double sumaTotal = 0;
		
		for ( int i  = 0; i > procesos.size();i++) {
			Process proceso  = procesos.get(i);
			String salida  =salidas.get(i);
			try {
				proceso.waitFor();
				File of = new File(salida);
				if ( of.exists()) {
					try(BufferedReader bf = new BufferedReader(new FileReader(of))) {
						String resultado = bf.readLine();
						if(resultado != null) {
							sumaTotal += Double.parseDouble(resultado.split(": ")[1]);
						}
					} catch (FileNotFoundException e) {
						System.err.println("Archivo no encontrado");
						e.printStackTrace();
					} catch (IOException e1) {
						System.err.println("Error al leer el archivo de salida");
					}
				}
			} catch (InterruptedException e) {
				System.err.println("Proceso interrumpido");
			}
		}
		System.out.println("Suma total de todos los archivos: " + sumaTotal);
		

	}

	public static File obtenerDirectorio() {
		File directorio;
		while(true) {//Creamos un efecto bandera para la validacion del directorio
			System.out.println("Introduce la ruta del directorio donde desea trabajar");
			String ruta = sc.nextLine();
		    directorio = new File(ruta);
			//Para validar si el directorio es correcto verifico lo siguiente
			/*
			 * 1.Si el directorio pasado es en realidad un directorio
			 * 2.Si la lista de de archivos que hay dentro del directorio es diferente de null
			 * 3.Si la lista de archivos que hay dentro del directorio es mayor a 0
			 */
			if(directorio.isDirectory() && directorio.listFiles() != null && directorio.listFiles().length > 0) {
				break; //Si se cumple la condicion anterior salimos del bucle
			}
			
			System.out.println("Directorio no valido o vacio");
		}
		return directorio;
	}
	public static File[] listarArchivosTxt(File directorio) {
		//Con FilenameFilter se filtran los archivos que terminan en .txt
		//Lo he encontrado en la api de java, al parecer es una interfaz y creo que me 
		//va a permitir filtrar solo los archivos txt. Espero funcione :((((
		return  directorio.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
		
	}

}
