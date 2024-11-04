package _01_Procesos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class _10_Procesos {

	public static void main(String[] args) throws IOException {
//		
//		
//		
//		List<ProcessBuilder> builders = Arrays.asList(
//				new ProcessBuilder("cmd" ,"/c", "echo" , "Hola"),
//				new ProcessBuilder("findstr" , "Hola")
//				);
//		
//		Process pipeline = ProcessBuilder.startPipeline(builders);
		
		// Lista tipada para especificar que contiene ProcessBuilders
				List<ProcessBuilder> builders = Arrays.asList(
					new ProcessBuilder("cmd", "/c", "echo", "Hola"),
					new ProcessBuilder("findstr", "Hola")
				);
				
				// Ejecutar la tubería de procesos
				List <Process> pipeline = ProcessBuilder.startPipeline(builders);
				
				
	}

}
