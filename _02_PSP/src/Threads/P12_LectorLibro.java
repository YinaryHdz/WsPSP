package Threads;

public class P12_LectorLibro implements Runnable{

	P12_Libro libro;
	
	public P12_LectorLibro(P12_Libro libro) {
		this.libro = libro;
	}

	@Override
	public void run() {
		//Synchronized garantiza que todos los hilos se guarden en la memoria   
		synchronized (libro) {
			System.out.println(Thread.currentThread().getName() + " esperando libro completado: " + libro.getTitulo());
			try {
				libro.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " se ha ejecutado");
		}
		
	}
}
