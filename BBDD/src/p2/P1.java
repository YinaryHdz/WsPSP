package p2;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P1 {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SQLException {

        File coches = new File("C:/dir1/coches.bin");
        insertarDatos(coches);
        visualizarDatosFichero(coches);

        // Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/DatosCoches", "root", "");
        actualizarBD(coches, con);
    }

    static void insertarDatos(File fichero){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))) {

            String numMatricula;
            String marca;
            double precio;
            String propietario;
            boolean salir = false;

            while (!salir) {
                System.out.println("introducir datos de coche: ");

                System.out.println("Nº matricula: ");
                numMatricula = sc.nextLine();
                dos.writeUTF(numMatricula);

                System.out.println("Marca: ");
                marca = sc.nextLine();
                dos.writeUTF(marca);

                System.out.println("Precio: ");
                precio = sc.nextDouble();
                dos.writeDouble(precio);

                sc.nextLine();
                System.out.println("Propietario (DNI): ");
                propietario = sc.nextLine();
                dos.writeUTF(propietario);

                System.out.println("Salir (S|N): ");
                if (sc.nextLine().charAt(0) == 'S') {
                    salir = true;
                }
            }
            dos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void visualizarDatosFichero (File fichero) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(fichero));

        while(dis.available() > 0){
            System.out.println("\nMatricula: " + dis.readUTF());
            System.out.println("\nMarca: " + dis.readUTF());
            System.out.println("\nPrecio: " + dis.readDouble());
            System.out.println("\nPropietario (DNI): " + dis.readUTF());
        }

        dis.close();
    }

    static  void actualizarBD (File fichero, Connection con) throws SQLException, IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(fichero));
        String numMatricula;
        String marca;
        double precio;
        String propietario;

        Statement st = null;
        String sentencia;

        while(dis.available() > 0){
            numMatricula = dis.readUTF();
            marca = dis.readUTF();
            precio = dis.readDouble();
            propietario = dis.readUTF();

            st = con.createStatement();
            sentencia = "INSERT INTO COCHES VALUES ('" + numMatricula + "','" + marca + "'," + precio + ",'" + propietario + "')";

            st.executeUpdate(sentencia);
        }

        st.close();
        dis.close();
    }
}


