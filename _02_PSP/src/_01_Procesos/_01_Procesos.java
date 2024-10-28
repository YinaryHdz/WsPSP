package _01_Procesos;

import java.io.IOException;

public class _01_Procesos {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("notepad.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
