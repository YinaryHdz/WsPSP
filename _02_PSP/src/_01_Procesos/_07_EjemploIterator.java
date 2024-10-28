package _01_Procesos;

import java.util.ArrayList;
import java.util.Iterator;

public class _07_EjemploIterator {

	public static void main(String[] args) {
		ArrayList<String>lista = new ArrayList<String>();
		lista.add("Jose");
		lista.add("Jesus");
		lista.add("Diego");
		lista.add("Juan");
		lista.add("Sergio");
		
//		for (String nombre: lista) {
//			System.out.println(nombre);
//			if(nombre.equals("Diego")) {
//				lista.remove("Diego");
//			}
//		}
//		
		
		
		Iterator<String> it = lista.iterator();
		while(it.hasNext()) {
			String nombre = it.next();
			System.out.println(nombre);
			if(nombre.equals("Diego")) {
				it.remove();
			}
		}
		
		System.out.println("-------------------");
		it = lista.iterator();
		while(it.hasNext()) {
			String nombre = it.next();
			System.out.println(nombre);
		}
		
	}

}
