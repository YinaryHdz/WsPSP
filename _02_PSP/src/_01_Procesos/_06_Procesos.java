package _01_Procesos;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class _06_Procesos {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pbuilder = new ProcessBuilder("");
		System.out.println("Directorio de trabajo por defecto: " + System.getProperty("user.dir"));
		pbuilder.directory(new File(System.getProperty("user.home")));
		
		Map<String, String> environment = pbuilder.environment();
		String systemPath  = environment.get("Path") + "C:dir1";
		environment.replace("Path", systemPath);
		System.out.println("Path: " + environment.get("Path"));
		
		environment.put("Saludos", "Hola a todos");
		//pbuilder.command("cmd.exe", "start", "cmd.exe","/k", "echo","%Saludos%");
		
		//Se cambia a dir2 para visualizar una salida de error
		pbuilder.command("cmd.exe", "start", "cmd.exe","/k", "dir","C:/dir2");
		
		pbuilder.redirectOutput(new File( "C:/dir1/salida_proc.txt"));
		pbuilder.redirectError(new File( "C:/dir1/salida_err.txt"));
		
		//Crear lista de variables de entorno
		
		Map variableEntorno  = pbuilder.environment();
		System.out.println("Variables de entorno: " + variableEntorno);
		
		List command = pbuilder.command();
		
		Iterator iter = command.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		//pbuilder.inheritIO();
		pbuilder.start();
		
	}
}
