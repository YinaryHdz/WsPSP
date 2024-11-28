package simulacionCasino;

import java.util.Random;

public class Ruleta {
	private int resultado;
	
	public synchronized void girarRuleta() {
		Random random = new Random();
		resultado = random.nextInt(37);//Genera un numero entre 0 y 36
		System.out.println("El resultado de la ruleta es: " + resultado);
		notifyAll();//Notifica a los jugadores que la ruleta ha girado
	}
	
	public synchronized int getResultado() {
		return resultado;
	}
	

}
