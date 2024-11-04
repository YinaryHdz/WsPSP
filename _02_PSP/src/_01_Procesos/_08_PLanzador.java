package _01_Procesos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class _08_PLanzador {
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Aplicacion a ejecutar: ");
		String aplicacion = sc.nextLine();
		
		ProcessBuilder pb = new ProcessBuilder("java","procesos.p8Lanzado", aplicacion);
		pb.directory(new File("bin"));
		pb.inheritIO();
		Process proceso = pb.start();
		int codigoFinalizacion = proceso.waitFor();
		if(codigoFinalizacion == 0) {
			System.out.println("Aplicación ejecutada correctamente");
		}
		else {
			System.out.println("Aplicación ejecutada incorrectamente. Código de error: " + codigoFinalizacion);
		}
	}
}
