package peluqueria;

public class Cliente extends Thread {
    private final int idCliente;
    private final Peluqueria peluqueria;

    public Cliente(int idCliente, Peluqueria peluqueria) {
        this.idCliente = idCliente;
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            peluqueria.entrarCliente(idCliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
