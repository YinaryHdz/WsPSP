package threads;

public class P9_MetodosThreadHilos implements Runnable {

	@Override
	public void run() {
		String nombreThread = Thread.currentThread().getName();
		System.out.println("["+ nombreThread + "]" + "Dentro del thread");
		System.out.println("["+ nombreThread + "]" + "Prioridad: " + Thread.currentThread().getPriority());
		Thread.yield();
		System.out.println("["+ nombreThread + "]" + "Id: " + Thread.currentThread().getId());
		System.out.println("["+ nombreThread + "]" + "Grupo: " + Thread.currentThread().getThreadGroup().getName());
		System.out.println("["+ nombreThread + "]" + "Contador del grupo del hilo: " + Thread.currentThread().getThreadGroup().activeCount());
	}

}
