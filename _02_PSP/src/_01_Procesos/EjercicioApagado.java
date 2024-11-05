package _01_Procesos;

import java.io.IOException;
import java.util.Scanner;

public class EjercicioApagado {


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
				hibernar();
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
			 System.out.println("Introduce el tiempo de espera (segundos) en el que deseas reiniciar el equipo:");
	            int espera = sc.nextInt();
	            sc.nextLine();  

	            System.out.println("¿Tu sistema operativo es Windows o Linux? (Escribe 'Windows' o 'Linux'):");
	            String sistemaOperativo = sc.nextLine().trim().toLowerCase();

	            if (sistemaOperativo.equals("windows")) {
	                Runtime.getRuntime().exec("shutdown /r /t " + espera);
	                System.out.println("El ordenador se reiniciará en: " + espera + " segundos (Windows).");

	            } else if (sistemaOperativo.equals("linux")) {
	                // Convertir segundos a minutos
	                int esperaMinutos = espera / 60;

	                if (esperaMinutos == 0) {
	                    esperaMinutos = 1;
	                }
	                Runtime.getRuntime().exec("shutdown -r " + esperaMinutos);
	                System.out.println("El ordenador se reiniciará en: " + esperaMinutos + " minutos (Linux).");
	            } else {
	                System.out.println("Sistema operativo no reconocido. Por favor, escribe 'Windows' o 'Linux'.");
	            }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void hibernar() {
		try {
			 System.out.println("Su equipo se colocara en hibernacion");
			 System.out.println("Introduce el tiempo de espera (segundos) en el que deseas hibernar el equipo");
			 int espera = sc.nextInt();
			 sc.nextLine();
			 
			 System.out.println("¿Tu sistema operativo es Windows o Linux? (Escribe 'Windows' o 'Linux'):");
	         String sistemaOperativo = sc.nextLine().trim().toLowerCase();
	         
	         if (sistemaOperativo.equals("windows")) {
	                Runtime.getRuntime().exec("powercfg -h on");
	                Thread.sleep(espera * 1000); // Convertir segundos a milisegundos
	                Runtime.getRuntime().exec("shutdown /h");
	                System.out.println("El ordenador hibernará en: " + espera + " segundos (Windows).");
	            } else if (sistemaOperativo.equals("linux")) {
	                Runtime.getRuntime().exec("shutdown -h " + espera);
	                System.out.println("El ordenador se apagará en: " + espera + " segundos (Linux).");
	            } else {
	            	// Hibernar en Linux (requiere permisos sudo)
	                Thread.sleep(espera * 1000);
	                Runtime.getRuntime().exec("systemctl hibernate");
	                System.out.println("El ordenador hibernará en: " + espera + " segundos (Linux).");
	            }
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void apagar() {
		 try {
			 System.out.println("Introduce el tiempo de espera (segundos) en el que deseas apagar el equipo");
			 int espera = sc.nextInt();
			 sc.nextLine(); 

            System.out.println("¿Tu sistema operativo es Windows o Linux? (Escribe 'Windows' o 'Linux'):");
            String sistemaOperativo = sc.nextLine().trim().toLowerCase();

            if (sistemaOperativo.equals("windows")) {
                Runtime.getRuntime().exec("C:\\Windows\\System32\\shutdown -s -t " + espera);
                System.out.println("El ordenador se apagará en: " + espera + " segundos (Windows).");
            } else if (sistemaOperativo.equals("linux")) {
                Runtime.getRuntime().exec("shutdown -h " + espera);
                System.out.println("El ordenador se apagará en: " + espera + " segundos (Linux).");
            } else {
                System.out.println("Sistema operativo no reconocido. Por favor, escribe 'Windows' o 'Linux'.");
            }
		} catch (IOException e) {
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

		

