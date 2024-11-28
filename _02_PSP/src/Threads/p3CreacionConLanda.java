package Threads;

public class p3CreacionConLanda {
    public static void main(String[] args) {
        Runnable miNunable =  () ->{
            System.out.println("Thread corriendo");
        };

        Thread thread = new Thread(miNunable);
        thread.start();
    }
}
