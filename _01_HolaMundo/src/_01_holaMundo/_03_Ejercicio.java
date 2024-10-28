package _01_holaMundo;

import java.io.File;
import java.io.IOException;

public class _03_Ejercicio {

	public static void main(String[] args) {
		String comand = "java -jar install.jar -install";
		ProcessBuilder pBuilder = new ProcessBuilder(comand.split("\\s"));
		pBuilder.directory(new File ("C:/dir1/JAR_Install"));
		boolean esWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		if(esWindows) {
			pBuilder.command().add(0, "cmd.exe");
			pBuilder.command().add(1, "start");
			pBuilder.command().add(2, "cmd.exe");
			pBuilder.command().add(3, "/k");
			pBuilder.command().add("C:\\dir1");
			
		}else {
			//Linux
		}
		System.out.println("Comando: " + pBuilder.command().toString());
		try {
			pBuilder.inheritIO();
			pBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
