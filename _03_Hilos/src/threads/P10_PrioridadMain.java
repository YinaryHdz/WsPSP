package threads;

public class P10_PrioridadMain {

	public static void main(String[] args) throws InterruptedException {
		P10_PrioridadHilo h1 = new P10_PrioridadHilo();
		P10_PrioridadHilo h2 = new P10_PrioridadHilo();
		P10_PrioridadHilo h3 = new P10_PrioridadHilo();

		//La prioridad va entre 1(minima) y 10(maxima)
		h1.setPriority(5);
		//h1.setPriority(Thread.NORM_PRIORITY);
		h2.setPriority(10);
		h3.setPriority(1);
		
		h1.start();
		h2.start();
		h3.start();
		
		//En milisegundos
		Thread.sleep(1000);
		h1.pararHilo();
		h2.pararHilo();
		h3.pararHilo();
		
		System.out.println("Prioridad máxima: " + h2.getContador());
		System.out.println("Prioridad normal: " + h1.getContador());
		System.out.println("Prioridad mínima: " + h3.getContador());
	}

}
