package compradoresAnsiosos;

public class Cliente extends Thread {
    private final int idCliente;
    private final Tienda tienda;

    public Cliente(int idCliente, Tienda tienda) {
        this.idCliente = idCliente;
        this.tienda = tienda;
    }

    @Override
    public void run() {
        try {
            tienda.entrarCliente(idCliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

