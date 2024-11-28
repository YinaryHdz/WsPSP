package Threads;

public class p6DosHilos {
    private static int numeroTarea = 0;
    private final int id = numeroTarea++;
    private int cuentaAtras = 10;

    public p6DosHilos(int cuentaAtras) {
        this.cuentaAtras = cuentaAtras;
    }

    public String status() {
        return "#" + id + "(" + (cuentaAtras > 0 ? cuentaAtras : "Despegue") + ")";
    }

    public static void main(String[] args) {
        p6DosHilos lanz1 = new p6DosHilos(9);
        p6DosHilos lanz2 = new p6DosHilos(7);

        Runnable r1 = () -> {
            while (lanz1.cuentaAtras-- > 0) {
                System.out.println(lanz1.status());
            }
        };

        Runnable r2 = () -> {
            while (lanz2.cuentaAtras-- > 0) {
                System.out.println(lanz2.status());
            }
        };

        // r1.run();
        //r2.run();

        new Thread(r1).start();
        new Thread(r2).start();

        System.out.println("Comienza la cuenta atras");
    }
}
