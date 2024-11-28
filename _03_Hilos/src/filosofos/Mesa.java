package filosofos;

import java.util.concurrent.locks.ReentrantLock;

//Gestiona los cubiertos y los filosofos
public class Mesa {
	//ReentrantLock es una clase de Java, 
	//Es una herramienta avanzada para gestionar bloqueos explícitos en 
	//lugar de usar la sincronización implícita (synchronized).
	private final ReentrantLock [] cubiertos;
	//ReentrantLock[] cubiertos es el modelo que utilizamos 
	//para representar los cubiertos como recursos compartidos que pueden
	//ser bloqueados y desbloqueados por los filósofos.
	public Mesa(int numFilosofos) {
		cubiertos = new ReentrantLock[numFilosofos];//// Crear el arreglo de cubiertos
		for (int i = 0; i < numFilosofos; i++) {
			cubiertos[i] = new ReentrantLock();//// Inicializar cada cubierto como un ReentrantLock
		}
	}
	
	//Intentar tomar los cubiertos
	public void tomarCubiertos(int idFilosofo) {
		int izquierdo = idFilosofo;
		int derecho = (idFilosofo + 1) % cubiertos.length;//Hace la mesa circular( vuelve al principio)
		
		// Para evitar interbloqueos, intentamos adquirir los cubiertos en orden
		if(idFilosofo % 2 ==0) {
			cubiertos[izquierdo].lock();// Primero el cubiertos izquierdo
			cubiertos[derecho].lock();// Luego el cubierto derecho
		}else {
			cubiertos[derecho].lock();// Primero el cubiertos derecho
			cubiertos[izquierdo].lock();// Luego el cubierto izquierdo
		}	
	}
	//Liberar los cubiertos
	public void soltarCubiertos(int idFilosofo) {
		int izquierdo = idFilosofo;
		int derecho = (idFilosofo + 1) % cubiertos.length;
		cubiertos[izquierdo].unlock();//Liberar el izquierdo
		cubiertos[derecho].unlock();//Liberar el derecho
	}
}
