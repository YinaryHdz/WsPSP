package compradoresAnsiosos;

import java.util.concurrent.Semaphore;

public class Tienda {
    private final int totalProductos; // Total de productos disponibles.
    private int productosDisponibles;  // Productos disponibles.
    private final Semaphore entrada;  // Semáforo para controlar el paso de clientes.

    public Tienda(int totalProductos) {
        this.totalProductos = totalProductos;
        this.productosDisponibles = totalProductos;
        this.entrada = new Semaphore(1);  // Solo una persona entra a la vez.
    }

    // Método que simula la compra de un producto.
    public synchronized boolean comprarProducto() {
        if (productosDisponibles > 0) {
            productosDisponibles--;
            return true;
        }
        return false;
    }

    // Método para que los clientes intenten acceder a la tienda.
    public void entrarCliente(int idCliente) throws InterruptedException {
        int intentos = 0;

        while (intentos < 10) {
            // Intentar entrar a la tienda
            entrada.acquire();  // Solo un cliente puede entrar a la vez.
            System.out.println("Cliente " + idCliente + " está intentando entrar.");

            // Intentar comprar un producto
            if (comprarProducto()) {
                System.out.println("Cliente " + idCliente + " compró un producto.");
                entrada.release();  // Cliente se va, libera la entrada.
                return;  // Cliente compra y se va.
            } else {
                System.out.println("Cliente " + idCliente + " no pudo comprar el producto. Intentando nuevamente.");
                intentos++;
            }

            entrada.release();  // Cliente sale de la tienda y libera la entrada.
            Thread.sleep(1000);  // Simula el tiempo entre intentos.

        }

        // Si el cliente agotó sus intentos y no compró, se va.
        System.out.println("Cliente " + idCliente + " se ha ido después de 10 intentos fallidos.");
    }
}

