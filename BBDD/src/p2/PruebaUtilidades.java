package p2;

import java.sql.Connection;
import java.util.Collections;

public class PruebaUtilidades {
    public static void main(String[] args) {
        Utilidades miConn = null;
        Connection conn = null;

        String fichXML = "C:/dir1/properties.xml";
        miConn = new Utilidades(fichXML);

        Connection connection = conn =  miConn.getConnection();


    }
}
