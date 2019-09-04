package service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import util.SqlConstaint;

import java.sql.*;

public class Service {



    public Statement connect(Connection connection)  {
        Statement state = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            state = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return state;
    }

    public void createTable() {

    }

    public void dropTables(Statement state) throws SQLException {
        state.execute(SqlConstaint.DROP_TABLE_CONTACT);
        state.execute(SqlConstaint.CONTACT_PHONES);
        state.execute(SqlConstaint.CONTACT_GROUPS);
    }

    public void createTables(Statement state) throws SQLException {
        state.execute(SqlConstaint.TABELA_CONTATO);
        state.execute(SqlConstaint.TABELA_PHONES);
        state.execute(SqlConstaint.TABELA_GROUPS);
        state.execute(SqlConstaint.CONTACT_PHONES);
        state.execute(SqlConstaint.CONTACT_GROUPS);
        System.out.println("Tabelas criadas");

    }

    public void insertContact(Statement state, String firstName, String lastName, String email) throws SQLException {
        String insert = String.format("insert into contact(first_name, last_name, mail) values('%s', '%s', '%s')", firstName, lastName, email);
        state.execute(insert);
        System.out.printf("Usuário %s inserido com sucesso%n", firstName);
    }

    public void insertPhones(Statement state, String phone) throws SQLException {
        String insert = String.format("insert into phones(phone) values('%s')", phone);
        state.execute(insert);
        System.out.printf("Telefone %s inserido com sucesso%n", phone);
    }

    public void insertGroups(Statement state, String description) throws SQLException {
        String insert = String.format("insert into groups(description) values('%s')", description);
        state.execute(insert);
        System.out.printf("Grupo %s inserido com sucesso%n", description);
    }

    public void findAllContact(Statement state) throws SQLException {
        ResultSet resultSet = state.executeQuery("select * from contact;");
        while (resultSet.next()) {
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String mail = resultSet.getString("mail");
            System.out.println(String.format("Contato: %s , %s, %s",first_name, last_name, mail));
        }
    }

    public void findContactById(Statement state, Integer id) throws SQLException {
        ResultSet resultSet = state.executeQuery(String.format("select * from contact where contact_id = %d ;", id));
        while (resultSet.next()) {
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String mail = resultSet.getString("mail");
            System.out.println(String.format("Contato: %s , %s, %s",first_name, last_name, mail));
        }
    }

    public void findContactByName(Statement state, String fname) throws SQLException {
        ResultSet resultSet = state.executeQuery(String.format("select * from contact where first_name = '%s' ;", fname));
        while (resultSet.next()) {
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String mail = resultSet.getString("mail");
            System.out.println(String.format("Contato: %s , %s, %s",first_name, last_name, mail));
        }
    }
}
