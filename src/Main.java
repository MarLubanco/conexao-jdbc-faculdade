import java.sql.*;

public class Main {

    public static void connect() throws SQLException {
        Service service = new Service();
        Connection connection = null;
        Statement state = service.connect(connection);
        service.dropTables(state);
        System.out.println("Banco de dados limpo");
        System.out.println("Banco de dados conectado");
        service.createTables(state);
        service.insertContact(state, "thome", "marcelo", "asdalll");
        service.insertContact(state, "caraii", "marcelo", "asdalll");
        service.insertPhones(state, "99955566");
        service.insertGroups(state, "GRUPO LEGAL");
        service.findAllContact(state);
        service.findContactByName(state,"thome");
        service.findContactById(state,12);

    }
    public static  void main(String args[]) throws SQLException {
        connect();
    }
}

