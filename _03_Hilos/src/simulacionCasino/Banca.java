package simulacionCasino;

public class Banca {
	public int saldo;
	
	public Banca (int saldoInicial) {
		this.saldo = saldoInicial;
	}
	
	public synchronized boolean aceptarApuesta(int apuesta) {
		saldo += apuesta;//Aumenta el saldo con la apuesta
		return true;
	}
	
	public synchronized void pagarGanancia(int ganancia) {
		if (saldo >= ganancia) {
			saldo -= ganancia;//Reduce el saldo al pagar la ganancia
		}else {
			System.out.println("La banca no tine suficiente saldo para pagar");
			
		}
	}
	
	public synchronized int getSaldo() {
		return saldo;
	}
	
}
