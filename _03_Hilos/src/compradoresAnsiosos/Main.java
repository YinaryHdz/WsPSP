package compradoresAnsiosos;

public class Main {
    public static void main(String[] args) {
        int totalProductos = 100;  // 100 productos disponibles
        int totalClientes = 300;  // 300 compradores

        Tienda tienda = new Tienda(totalProductos);

        // Crear e iniciar hilos de clientes.
        for (int i = 1; i <= totalClientes; i++) {
            new Cliente(i, tienda).start();
            try {
                Thread.sleep((int) (Math.random() * 10));  // Simula llegada de clientes en intervalos aleatorios.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

