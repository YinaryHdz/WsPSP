package _01_Procesos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
public class _09_Procesos {

	public static void main(String[] args) throws IOException {
//		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "start", "cmd.exe" ,"/c","di","C:\\dir1");
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "start", "cmd.exe" ,"/c","findstr","hola");
		Process p = pb.start();
		
		PrintWriter alProceso = new PrintWriter( 
			new OutputStreamWriter(p.getOutputStream(), "UTF-8")
		);
		
		alProceso.println("11111111\n2222hola233\n333\nhola");
		
		alProceso.close();
		
//		pb.inheritIO();
		BufferedReader salidaProceso = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String linea;
		while((linea = salidaProceso.readLine())!=null){
			System.out.println("stdout: "+ linea);
		}
		salidaProceso.close();
		BufferedReader errorProceso = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while((linea = errorProceso.readLine())!=null){
			System.out.println("stderr: "+ linea);
		}
		errorProceso.close();

	}

}
