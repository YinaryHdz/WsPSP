package _01_Procesos;

import java.io.IOException;
import java.util.Scanner;

public class _04_Ejercicio {


	static Scanner sc;
	public static void main(String[] args) {
		int opcion = 0;
		do {
			opcion = menu();
			switch (opcion) {
			case 1:
				apagar();
				break;
			case 2:
				suspender();
				break;
			case 3:
				reiniciar();
				break;
			case 5:
				System.out.println("Saliendo del programa....");
				break;
			default:
				System.out.println("Ingrese una opcion valida");
			}
		}while(opcion != 5 );

	}
	
	private static void reiniciar() {
		try {
			 System.out.println("Introduce el tiempo de espera (segundos) en el que deseas reiniciar el equipo");
			 int espera = sc.nextInt();
			Runtime.getRuntime().exec("C:\\Windows\\System32\\shutdown -s -r " + espera);
			System.out.println("El ordenador se reiniciara en: " + espera + " segundos");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void suspender() {
		try {
			 System.out.println("Su equipo sera suspendido");
			Runtime.getRuntime().exec("rundll32.exe powrprof.dll,SetSuspendState Sleep");
			System.out.println("Suspendiendo...");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void apagar() {
		 try {
			 System.out.println("Introduce el tiempo de espera (segundos) en el que deseas apagar el equipo");
			 int espera = sc.nextInt();
			Runtime.getRuntime().exec("C:\\Windows\\System32\\shutdown -s -t " + espera);
			System.out.println("El ordenador se apagara en: " + espera + " segundos");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static int menu() {
		int opcion = 0;
		System.out.println("Ingrese la opcion que desee");
		System.out.println("1. Apagar");
		System.out.println("2. Suspender");
		System.out.println("3. Reiniciar");
		System.out.println("4. Salir del programa");
		opcion  = sc.nextInt();
		return opcion;
	}


}

		

