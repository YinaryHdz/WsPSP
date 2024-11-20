package semaforoP14;

public class SemaforoMain {

	public static void main(String[] args) throws InterruptedException {
		Semaforo almacen = new Semaforo();
		Thread productor1  =new Thread(() -> {
			for(int i = 0; i < 20; i++) {
				almacen.producir("Productor1");
			}
		});
		
		Thread consumidor1  =new Thread(() -> {
			for(int i = 0; i < 10; i++) {
				almacen.consumir("Consumidor1");
			}
		});
		
		Thread consumidor2  =new Thread(() -> {
			for(int i = 0; i < 10; i++) {
				almacen.consumir("Consumidor2");
			}
		});
		
		productor1.start();
		consumidor1.start();
		consumidor2.start();
		
		productor1.join();//Espera a que termine
		consumidor1.join();
		consumidor2.join();
		
		System.out.println("Fin de la prueba");

	}

}
