package _01_Procesos;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class _12_Procesos {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ProcessBuilder pb = new ProcessBuilder("java", "-version");
		pb.redirectErrorStream(true);
		
		File dir = new File("C:/dir1");
		File log = new File(dir,"java-version.log");
		
		pb.redirectOutput(Redirect.appendTo(log));
		
		Process p = pb.start();
		
		int salida = p.waitFor();
		
		System.out.println("Codigo salida: "+ salida);

	}

}
