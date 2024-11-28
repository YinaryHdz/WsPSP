package Threads;

public class p1CreacionRunable implements Runnable{

    public static void main(String[] args) {
        p1CreacionRunable miRunable = new p1CreacionRunable();
        Thread thread = new Thread(miRunable);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Hilo Corriendo");
    }
}
