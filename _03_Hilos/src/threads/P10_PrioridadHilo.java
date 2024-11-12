package threads;

public class P10_PrioridadHilo extends Thread {

		long c = 0;
		
		boolean stopHilo = false;
		
		public long getContador() {
			return c;
		}
		
		public void pararHilo() {
			stopHilo = true;
		}
		
		public void run() {
			while(!stopHilo) {
				c++;
			}
		}
		

	

}
