package threads;

public class P11_Contador {
	private int c;
	
	public P11_Contador (int c) {
		this.c = c;
	}
	
	public void incrementa (int c) {
		c++;
	}
	
	public void decrementa (int c) {
		c--;
	}

	public int valor() {    
		return c;
	}

	
	
	
}
