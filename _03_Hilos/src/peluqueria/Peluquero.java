package peluqueria;

public class Peluquero extends Thread {
    private final int idPeluquero;
    private final Peluqueria peluqueria;

    public Peluquero(int idPeluquero, Peluqueria peluqueria) {
        this.idPeluquero = idPeluquero;
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            peluqueria.atenderCliente(idPeluquero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

