package Threads;

public class p8ThreadClaseAnonima {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread con clase anonima");
            }
        };
        thread.start();
    }
}
