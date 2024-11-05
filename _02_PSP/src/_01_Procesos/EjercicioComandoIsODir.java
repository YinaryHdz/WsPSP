package _01_Procesos;

import java.io.File;
import java.io.IOException;

public class EjercicioComandoIsODir {

	public static void main(String[] args) throws IOException, InterruptedException {
		/*
		 * Programa que ejecute el comando ls o dir.
			Modifica el valor de la propiedad user.dir. En la misma aplicación, cambiar el directorio de trabajo a la carpeta c:/temp o
			/tmp, dependiendo del sistema operativo.
			Muestra el valor devuelto por el método directory():
			Después de crear la instancia de ProcessBuilder
			Después de cambiar el valor de user.dir
			Después de cambiar el directorio de trabajo al directorio temporal del sistema c:/temp o /tmp
		 */
		
		String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        boolean esWindows = sistemaOperativo.contains("win");

        // Definir el comando a ejecutar dependiendo del sistema operativo
        String comando = esWindows ? "cmd /c dir" : "ls";
        ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
        System.out.println("Directorio actual después de crear ProcessBuilder: " + pb.directory());
        String directorioUsuario = System.getProperty("user.dir");
        System.out.println("Directorio de usuario actual (user.dir): " + directorioUsuario);

        System.setProperty("user.dir", directorioUsuario + "/nuevo-directorio");
        System.out.println("Directorio de usuario después de cambiar user.dir: " + System.getProperty("user.dir"));

        // Cambio del directorio de trabajo
        File directorioTemporal = esWindows ? new File("C:/temp") : new File("/tmp");

        if (!directorioTemporal.exists()) {
            System.out.println("El directorio temporal no existe, creando: " + directorioTemporal.getAbsolutePath());
            directorioTemporal.mkdir();
        }

        pb.directory(directorioTemporal);
        System.out.println("Directorio de trabajo cambiado a: " + pb.directory());

        // Ejecutar el comando en el directorio temporal
        Process proceso = pb.start();
        proceso.waitFor();  

	}

}
