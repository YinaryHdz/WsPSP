package Threads;

public class P11_Main {

	public static void main(String[] args) {
		P11_Contador contador = new P11_Contador(100);
		
		P11_Sumador sumador = new P11_Sumador("Sumador", contador);
		P11_Restador restador = new P11_Restador("Restador", contador);
		
		Thread hiloSumador = new Thread(sumador);
		Thread hiloRestador = new Thread(restador);
		
		hiloSumador.start();
		hiloRestador.start();
		
		try {
			hiloSumador.join();
			hiloRestador.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Valor final del contador: " + contador.valor());
		

	}

}
