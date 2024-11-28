package Threads;

public class P12_EscritorLibro implements Runnable {
P12_Libro libro;
	
	public P12_EscritorLibro(P12_Libro libro) {
		this.libro = libro;
	}

	
	public void run() {
		synchronized (libro) {
			System.out.println("Autor esta comenzando libro: " + libro.getTitulo());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			libro.setLibroCompletado(true);
			System.out.println("El libro se ha completado");
			libro.notify();
			System.out.println("Notifica a un lector");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			libro.notify();
			
		}
	}
}
