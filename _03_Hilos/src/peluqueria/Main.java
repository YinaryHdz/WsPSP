package peluqueria;

public class Main {
    public static void main(String[] args) {
        int numSillas = 5;
        int numPeluqueros = 3;
        int numClientes = 10;

        Peluqueria peluqueria = new Peluqueria(numSillas);

        // Crear e iniciar hilos de peluqueros.
        for (int i = 1; i <= numPeluqueros; i++) {
            new Peluquero(i, peluqueria).start();
        }

        // Crear e iniciar hilos de clientes.
        for (int i = 1; i <= numClientes; i++) {
            new Cliente(i, peluqueria).start();
            try {
                Thread.sleep((int) (Math.random() * 1000)); // Clientes llegan en intervalos aleatorios.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

