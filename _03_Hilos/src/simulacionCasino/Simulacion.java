package simulacionCasino;

public class Simulacion {

	public static void main(String[] args) {
		int saldoInicialJugador = 1000;
        int saldoInicialBanca = 50000;
        Ruleta ruleta = new Ruleta();
        Banca banca = new Banca(saldoInicialBanca);

        // Crear jugadores con estrategias.
        for (int i = 0; i < 4; i++) {
            new Jugador(i, saldoInicialJugador, ruleta, banca, "Número").start();
            new Jugador(i + 4, saldoInicialJugador, ruleta, banca, "Par/Impar").start();
            new Jugador(i + 8, saldoInicialJugador, ruleta, banca, "Martingala").start();
        }

        // Simular ruleta.
        while (true) {
            ruleta.girarRuleta();
            try {
                Thread.sleep(3000); // Tiempo entre tiradas.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

	}

}
//En la parte de arriba el bucle es infinito debido a que la ruleta nunca se detendra, se puede hacer un break para hacerlo parar
//En este caso se detendra cuando todos los jugadores se queden sin dinero
/*
public class Simulacion {
    public static void main(String[] args) {
        int saldoInicialJugador = 1000;
        int saldoInicialBanca = 50000;
        Ruleta ruleta = new Ruleta();
        Banca banca = new Banca(saldoInicialBanca);

        // Crear jugadores con estrategias.
        Jugador[] jugadores = new Jugador[12];
        for (int i = 0; i < 4; i++) {
            jugadores[i] = new Jugador(i, saldoInicialJugador, ruleta, banca, "Número");
            jugadores[i + 4] = new Jugador(i + 4, saldoInicialJugador, ruleta, banca, "Par/Impar");
            jugadores[i + 8] = new Jugador(i + 8, saldoInicialJugador, ruleta, banca, "Martingala");
        }

        // Iniciar hilos de jugadores.
        for (Jugador jugador : jugadores) {
            jugador.start();
        }

        // Simular ruleta.
        while (true) {
            boolean hayJugadoresActivos = false;
            for (Jugador jugador : jugadores) {
                if (jugador.estaActivo()) {
                    hayJugadoresActivos = true;
                    break;
                }
            }

            if (!hayJugadoresActivos) {
                System.out.println("Todos los jugadores se han quedado sin saldo. Fin de la simulación.");
                break;
            }

            ruleta.girarRuleta();
            try {
                Thread.sleep(3000); // Tiempo entre tiradas.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
*/