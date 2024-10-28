package _01_Procesos;

import java.io.IOException;

public class _02_Procesos {

	public static void main(String[] args) throws IOException {
		String directorioHome = System.getProperty("user.home");
		System.out.println("Directorio HOME: " + directorioHome);
		boolean esWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		System.out.println("OS NAME: " + System.getProperty("os.name") + " ¿es windows? " + esWindows);
		if(esWindows) {
			Runtime.getRuntime().exec(String.format("cmd.exe /c start cmd.exe /k dir %s", directorioHome));
		}else {
			Runtime.getRuntime().exec(String.format("sh -c ls %s", directorioHome));
		}

	}

}
