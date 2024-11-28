package Threads;

public class p4ExpresionesLanda {
    public static void main(String[] args) {
        OpercionSuma suma = (a,b) -> a + b;
        int result= suma.Operar(5,4);
        System.out.println(result);
    }

}
interface OpercionSuma{
    int Operar(int a, int b);
}
