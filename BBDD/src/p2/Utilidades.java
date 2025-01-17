package p2;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Utilidades {

    public String dbms;
    public String database_name;
    public String user_name;
    public String password;
    public String server_name;
    public int port_number;

    private Properties prop;

    public Utilidades (String propertiesFileName) throws IOException {
        this.setProperties(propertiesFileName);
    }

    private void setProperties(String propertiesFileName) throws IOException {
        this.prop = new Properties();
        prop.loadFromXML(Files.newInputStream(Paths.get(propertiesFileName)));

        this.dbms = this.prop.getProperty("dbms");
        this.database_name = this.prop.getProperty("database_name");
        this.user_name = this.prop.getProperty("user_name");
        this.password = this.prop.getProperty("password");
        this.server_name = this.prop.getProperty("server_name");
        this.port_number = Integer.parseInt(this.prop.getProperty("port_number"));

        System.out.println("Estableciendo propiedades:");
        System.out.println("dbms: " + dbms);
        System.out.println("database_name: " + database_name);
        System.out.println("user_name: " + user_name);
        System.out.println("password: " + password);
        System.out.println("server_name: " + server_name);
        System.out.println("port_number: " + port_number);
    }

    public Connection getConnection(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.user_name);
        connectionProps.put("password", this.password);

        // Oracle: jdbc:oracle:thin:@192.168.10.7:1521:DatosCoches

        if(this.dbms.equals("mysql")){
            try {
                conn = DriverManager.getConnection("jdbc" + ":" + this.dbms + "://" + this.server_name + ":" + this.port_number + "/" + this.database_name, connectionProps);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Conectado a BD");
        return conn;
    }

    public static void closeConnection(Connection conn){
        System.out.println("liberando recursos abiertos...");
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void printSQLException(SQLException e) {

        while (e != null) {
            if (e instanceof SQLException) {
                //Estado ANSI
                e.printStackTrace(System.err);
                System.err.println("SQLState: "
                        + ((SQLException) e).getSQLState());
                //Códio de error propio de cada gestor de BD
                System.err.println("Error Code: "
                        + ((SQLException) e).getErrorCode());
                //Mensaje textual
                System.err.println("Message: " + e.getMessage());

                //Objetos desencadenantes de la excepción
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
                //Cualquier otra excepción encadenada
                e = e.getNextException();

            }
        }
    }


}
