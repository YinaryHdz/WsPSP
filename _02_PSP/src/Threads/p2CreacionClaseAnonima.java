package Threads;

public class p2CreacionClaseAnonima {
    public static void main(String[] args) {
        Runnable miRunabble = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hilo corriendo");
            }
        };

        Thread thread = new Thread(miRunabble);
        thread.start();



    }
}
