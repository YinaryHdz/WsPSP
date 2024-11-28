package Threads;

public class p5SinLanda {
    public static void main(String[] args) {
        Suma suma = new Suma();
        int resultado = suma.Operar(5,4);
        System.out.println(resultado);
    }
}
interface OpercionSumaSinLanda{
    int Operar(int a, int b);
}

class Suma implements OpercionSuma{

    @Override
    public int Operar(int a, int b) {
        return a+b;
    }
}