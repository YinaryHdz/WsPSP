package Threads;

public class p7HeredandoDeThread extends Thread {

    public void run(){
        System.out.println("Thread corriendose");
    }
    public static void main(String[] args) {
        p7HeredandoDeThread miThread = new p7HeredandoDeThread();
        miThread.start();

    }
}
