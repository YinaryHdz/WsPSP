package _01_holaMundo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InstallApp {

	public static void main(String[] args) {
		if (args.length ==2 && args[0].equals("-install")) {
			String directorio = args[1];
			System.out.println("Instalando el directorio " +directorio);
			File dir = new File(directorio);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			File file = new File(dir, "installacion.log");
			try {
				FileWriter writer = new FileWriter(file);
				writer.write("Archivo de instalacion n creado en: " + file.getAbsolutePath());
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			System.out.println("Uso incorrecto, utilizar: -install<directorio>");
		}

	}

}
