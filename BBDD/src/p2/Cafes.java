package p2;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class Cafes {

    public static final String SELECT_CAFES_QUERY = "SELECT CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";
    public static final String SEARCH_CAFES_QUERY = "SELECT * FROM CAFES WHERE CAF_NOMBRE=?";
    public static final String INSERT_CAFES_QUERY = "INSERT INTO CAFES VALUES (?,?,?,?,?)";
    public static final String DELETE_CAFES_QUERY = "DELETE FROM CAFES WHERE CAF_NOMBRE = ?";

    public void verTabla() throws IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = new Utilidades("C:/dir1/properties.xml").getConnection();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SELECT_CAFES_QUERY);
            while (rs.next()) {
                String nombreCafe = rs.getString("CAF_NOMBRE");
                int proveedor = rs.getInt("PROV_ID");
                float precio = rs.getFloat("PRECIO");
                int ventas = rs.getInt("VENTAS");
                int total = rs.getInt("TOTAL");
                System.out.println(nombreCafe + "\t" + proveedor + "\t" + precio + "\t" + ventas + "\t" + total);
            }
        } catch (SQLException e) {
            Utilidades.printSQLException(e);
        } finally {
            try {
                if (rs != null) {

                    rs.close();

                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    Utilidades.closeConnection(con);
                }
            } catch (SQLException e) {
                Utilidades.printSQLException(e);
            }
        }

    }

    public void buscar(String nombre) throws IOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = new Utilidades("C:/dir1/properties.xml").getConnection();
        try {
            stmt = con.prepareStatement(SEARCH_CAFES_QUERY);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombreCafe = rs.getString("CAF_NOMBRE");
                int proveedor = rs.getInt("PROV_ID");
                float precio = rs.getFloat("PRECIO");
                int ventas = rs.getInt("VENTAS");
                int total = rs.getInt("TOTAL");
                System.out.println(nombreCafe + "\t" + proveedor + "\t" + precio + "\t" + ventas + "\t" + total);
            }
        } catch (SQLException e) {
            Utilidades.printSQLException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    Utilidades.closeConnection(con);
                }
            } catch (SQLException e) {
                Utilidades.printSQLException(e);
            }
        }
    }

    public void insertar (String nombre, int provid, float precio, int ventas, int total){

    }

    public void borrar (String nombre){

    }

    public void modificarPrecio(float porcentaje){
        Connection con = null;
        Statement stmt = null;
        ResultSet uprs = null;

        try {
            con = new Utilidades().getConnection();
            stmt = con.createStatement();
            uprs = stmt.executeQuery("SELECT * FROM CAFES");
            while(uprs.next()){
                float f = uprs.getFloat("PRECIO");
                uprs.updateFloat("PRECIO" ,  f*porcentaje);
                uprs.updateRow();
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void actualizarVentas(HashMap<String, Integer> ventas){
        PreparedStatement pstmt1, pstmt2;
        pstmt1= null;
        pstmt2 = null;

    }


}