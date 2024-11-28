package filosofos;

import java.util.Random;

//Representa a cada filósofo como un hilo
public class Filosofo implements Runnable {
	private final int id;
	private final Mesa mesa;
	private final Random random;
	
	public Filosofo(int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
		this.random = new Random();
	}
	@Override
	public void run() {
		while(true) {//Bucle infinito que alterna entre pensar y comer
			try {
				//El filósofo piensa
				System.out.println("El filosofo " + id + " esta pensando.");
				Thread.sleep(random.nextInt(6000) + 1000);
				
				//El filosofo intenta tomar los cubiertos
				System.out.println("El filosofo " + id + " esta intentando tomar los cubiertos.");
				mesa.tomarCubiertos(id);
				
				//Filosofo esta comiendo
				System.out.println("El filosofo " + id + " esta comiendo.");
				Thread.sleep(random.nextInt(6000) + 1000);
				
				//Filosofo suolta los cubiertos
				mesa.soltarCubiertos(id);
				System.out.println("El filosofo " + id + " solto los cubiertos.");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("El filosofo " + id + " ha sido interrumpido.");
				break;
			}
		}
		
	}

}
