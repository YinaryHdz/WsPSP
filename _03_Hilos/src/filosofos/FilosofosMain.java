package filosofos;

public class FilosofosMain {

	public static void main(String[] args) {
		final int NUM_FILOSOFOS = 5;
		
		Mesa mesa = new Mesa(NUM_FILOSOFOS);
		Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
		
		//Crear e iniciar los hilos para cada filosofo
		
		for (int i = 0; i< NUM_FILOSOFOS; i++) {
			filosofos[i] = new Filosofo(i, mesa);
			new Thread(filosofos[i], "Filosofo " + i ).start();
		}

	}

}
