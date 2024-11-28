package simulacionCasino;

import java.util.Random;

public class Jugador extends Thread{
	private int id;
	private int saldo;
    private Ruleta ruleta;
    private Banca banca;
    private String estrategia;
    private int apuestaBase = 10;
    private int apuestaActual;
    
	public Jugador(int id, int saldo, Ruleta ruleta, Banca banca, String estrategia) {
		this.id = id;
		this.saldo = saldo;
		this.ruleta = ruleta;
		this.banca = banca;
		this.estrategia = estrategia;
	}
	
	public void run() {
		Random random = new Random();
		while(saldo > 0) {
			synchronized(ruleta) {
				try {
					ruleta.wait();//Esperar que la ruleta gire
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				int resultadoRuleta = ruleta.getResultado();
				int apuesta = calcularApuesta(random);
				if(saldo >= apuesta) {
					saldo -= apuesta;
					banca.aceptarApuesta(apuesta);
					if(verificarGanancia(resultadoRuleta)) {
						int ganancia = calcularGanancia();
						saldo +=ganancia;
						banca.pagarGanancia(ganancia);
						System.out.println("Jugador " + id + " ha ganado " +ganancia+" euros");
					}else {
						System.out.println("Jugador " + id + " ha perdido " +apuesta+" euros");
					}
				}else {
					System.out.println("Jugador " + id + " no tiene saldo suficiente");
				}
				esperar(random.nextInt(2000));
				
			}			
		}
		System.out.println("Judador" + id + " ha perdido todo su dinero");
	}

	private void esperar(int tiempo) {
		 try {
	            Thread.sleep(tiempo);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		
	}

	private int calcularGanancia() {
		if(estrategia.equals("Martingala")) {
			return apuestaActual;
		}
		return apuestaBase;
	}

	private boolean verificarGanancia(int resultadoRuleta) {
		if(estrategia.equals("Numero")) {
			int numeroElegido = new Random().nextInt(36) + 1;
			return numeroElegido == resultadoRuleta;
		}else if(estrategia.equalsIgnoreCase("Par/Impar")) {
			boolean apuestaPar = new Random().nextBoolean();
			return (resultadoRuleta != 0) && ((resultadoRuleta % 2 == 0) == apuestaPar);
		}else if(estrategia.equalsIgnoreCase("Martingala")) {
			int numeroElegido = new Random().nextInt(36) + 1;
			return numeroElegido == resultadoRuleta;
		}
		return false;
	}

	private int calcularApuesta(Random random) {
        if (estrategia.equals("Número")) {
            return 360;
        } else if (estrategia.equals("Par/Impar")) {
            return 20;
        } else if (estrategia.equals("Martingala")) {
            apuestaActual = apuestaBase; // Reinicia la apuesta después de ganar.
            return 360;
        }
        return 0;
	}
	
	public boolean estaActivo() {
	    return saldo > 0;
	}
		
}
