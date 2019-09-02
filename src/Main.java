import java.sql.*;

public class Main {

    public static void connect(){

        String droptables = "DROP TABLE contact ";
        String droptable2 = "DROP TABLE phones ";
        String droptable3 = "DROP TABLE groups ";
        String tabelaContato = "CREATE TABLE IF NOT EXISTS contact(" +
                                                 " contact_id integer(10) PRIMARY KEY," +
                                                 " first_name VARCHAR2(300) NOT NULL," +
                                                 " last_name VARCHAR2(300) NOT NULL," +
                                                 " mail VARCHAR2(300) NOT NULL);";

        String tabelaPhones= "CREATE TABLE IF NOT EXISTS phones(" +
                " phone_id integer(10) PRIMARY KEY," +
                " phone VARCHAR2(300) NOT NULL);";

        String tabelaGroup= "CREATE TABLE IF NOT EXISTS groups(" +
                " group_id integer(10) PRIMARY KEY," +
                " description VARCHAR2(300) NOT NULL);";

        String contactPhones = "CREATE TABLE IF NOT EXISTS contact_phones (" +
                                " contact_id integer (10)," +
                "phones_id integer(10)," +
                "FOREIGN KEY (contact_id) REFERENCES  contact(contact_id)," +
                "FOREIGN KEY (phones_id) REFERENCES  phones(phone_id));";

        String contactGroups = "CREATE TABLE IF NOT EXISTS contact_groups (" +
                " contact_id integer (10)," +
                " groups_id integer(10)," +
                "FOREIGN KEY (contact_id) REFERENCES  contact(contact_id)," +
                "FOREIGN KEY (groups_id) REFERENCES  phones(group_id));";

        Connection connection = null;

        String insertContact = "INSERT INTO contact values(1,  'Marcelo', 'Thomé', 'lubanco@gmail.com');";
        String insertPhone = "INSERT INTO phones values(1, '9999888822');";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            Statement state = connection.createStatement();
//            state.execute(droptables);
//            state.execute(droptable2);
//            state.execute(droptable3);
            state.execute(tabelaContato);
            state.execute(tabelaPhones);
            state.execute(tabelaGroup);
//            state.execute(contactPhones);
//            state.execute(contactGroups);
//            state.execute(insertContact);
            state.execute(insertPhone);
            ResultSet resultSet = state.executeQuery("select * from contact;");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("first_name"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection !=null){
                    connection.close();
                }
            }catch(SQLException e ){
                e.printStackTrace();
            }
        }

    }
    public static  void main(String args[]){

        connect();
    }
}

