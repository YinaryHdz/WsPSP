package Threads;

public class P12_Main {

	public static void main(String[] args) {
		P12_Libro book = new P12_Libro("El señor de los anillos");
		P12_LectorLibro lectoraAna = new P12_LectorLibro(book);
		P12_LectorLibro lectorPepe = new P12_LectorLibro(book);
		
		Thread hiloAna = new Thread(lectoraAna, "Ana");
		Thread hiloPepe = new Thread(lectorPepe, "Pepe");
		
		hiloAna.start();
		hiloPepe.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		P12_EscritorLibro escritorLibro = new P12_EscritorLibro(book);
		
		Thread hiloEscritorLibro  = new Thread(escritorLibro);
		hiloEscritorLibro.start();
		

	}

}
