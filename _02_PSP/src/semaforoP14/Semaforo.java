package semaforoP14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Semaforo {

	private final int MAX_LIMITE = 20;
	private int producto = 0;
	private Semaphore productor = new Semaphore(MAX_LIMITE);
	private Semaphore consumidor = new Semaphore(0);
	private Semaphore mutex  =new Semaphore(1);
	
	public void producir (String nombreProductor) {
		System.out.println(nombreProductor + " intentando almacenar un producto ");
		try {
			productor.acquire();
			mutex.acquire();
			producto++;
			System.out.println(nombreProductor + " almacena un producto" + " Almacen con " + producto + " productos");
			mutex.release();
			Thread.sleep(ThreadLocalRandom.current().nextInt(0,500));
			consumidor.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consumir (String nombreConsumidor) {
		System.out.println(nombreConsumidor + " intentando retirar un producto ");
		try {
			consumidor.acquire();
			mutex.acquire();//Evita que el consumidor y el productor aumenten o disminuyan los productos  a la vez
			producto --;
			System.out.println(nombreConsumidor + " retira un producto. " + " Almacen con " + producto + " productos");
			mutex.release();
			Thread.sleep(ThreadLocalRandom.current().nextInt(0,500));
			productor.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
