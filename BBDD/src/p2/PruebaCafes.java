package p2;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;

public class PruebaCafes {
    public static void main(String[] args) {
        Cafes miCafe = new Cafes();
        try {
            miCafe.verTabla();
            System.out.println("----------");
            miCafe.buscar("Colombian");
            miCafe.modificarPrecio(200);
            miCafe.verTabla();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
