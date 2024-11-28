package peluqueria;

import java.util.concurrent.Semaphore;

public class Peluqueria {
    private final Semaphore sillas; // Controla las sillas de espera.
    private final Semaphore clientes; // Controla los clientes que esperan ser atendidos.
    private final Semaphore peluqueros; // Controla la disponibilidad de los peluqueros.
    private final int numSillas;

    public Peluqueria(int numSillas) {
        this.numSillas = numSillas;
        this.sillas = new Semaphore(numSillas, true); // Semáforo con número de sillas.
        this.clientes = new Semaphore(0, true); // Inicialmente no hay clientes.
        this.peluqueros = new Semaphore(0, true); // Inicialmente no hay peluqueros disponibles.
    }

    public void entrarCliente(int idCliente) throws InterruptedException {
        if (sillas.tryAcquire()) { // Intenta tomar una silla.
            System.out.println("Cliente " + idCliente + " se sienta en la sala de espera.");
            clientes.release(); // Informa que hay un cliente esperando.
            peluqueros.acquire(); // Espera a que un peluquero esté disponible.
            System.out.println("Cliente " + idCliente + " está siendo atendido.");
            sillas.release(); // Libera la silla después de ser atendido.
        } else {
            System.out.println("Cliente " + idCliente + " no encuentra silla y se marcha.");
        }
    }

    public void atenderCliente(int idPeluquero) throws InterruptedException {
        while (true) {
            clientes.acquire(); // Espera a que llegue un cliente.
            System.out.println("Peluquero " + idPeluquero + " está atendiendo a un cliente.");
            Thread.sleep((int) (Math.random() * 3000) + 1000); // Simula el tiempo de corte de pelo.
            System.out.println("Peluquero " + idPeluquero + " terminó de atender a un cliente.");
            peluqueros.release(); // Informa que está disponible para otro cliente.
        }
    }
}

