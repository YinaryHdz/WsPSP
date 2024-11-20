package threads;

import java.util.Random;
public class P11_Sumador extends Thread{
	private P11_Contador contador;
	private String nombre;
	
	public P11_Sumador (String nombre, P11_Contador contador) {
		this.nombre = nombre;
		this.contador= contador;
	}
	
	public void run() {
		Random rand = new Random();
		for(int i = 0; i < 1000; i++) {
			contador.incrementa(i);
			System.out.println("Nombre: " + "Incrementado. Nuevo valor:" + contador.valor());
			try {
				Thread.sleep(rand.nextInt(101)+ 50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
