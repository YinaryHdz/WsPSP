package _01_Procesos;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class _08_Procesos {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("notepad.exe");
		Process p = pb.start();
		
		boolean alive = p.isAlive();
		//Me dice si el proceso esta vivo
		System.out.println("Alive: " + alive);
		if(p.waitFor(10, TimeUnit.SECONDS)) {
			System.out.println("Proceso terminado");
		}else {
			System.out.println("Proceso no terminado");
		}
		p.destroy();
		//Devuelve el estado del proceso que se ha puesto en marcha.
		System.out.println("exitValue: " +p.exitValue());
		alive = p.isAlive();
		System.out.println("Alive: " + alive);
	}

}

