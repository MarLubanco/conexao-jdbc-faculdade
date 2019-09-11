package controller;

import model.Contato;
import model.Groups;
import model.Phone;
import util.SqlConstaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

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

    public void dropTables(Statement state) throws SQLException {
        state.execute(SqlConstaint.DROP_TABLE_CONTACT);
        state.execute(SqlConstaint.DROP_TABLE_PHONE);
        state.execute(SqlConstaint.DROP_TABLE_GROUP);
        state.execute(SqlConstaint.DROP_TABLE_CONTACT_GROUPS);
        state.execute(SqlConstaint.DROP_TABLE_CONTACT_PHONES);
    }

    public void createTables(Statement state) throws SQLException {
        state.execute(SqlConstaint.TABELA_CONTATO);
        state.execute(SqlConstaint.TABELA_PHONES);
        state.execute(SqlConstaint.TABELA_GROUPS);
        state.execute(SqlConstaint.CONTACT_PHONES);
        state.execute(SqlConstaint.CONTACT_GROUPS);
        System.out.println("Tabelas criadas");
    }

    public void insertContact(Statement state, Contato contato) throws SQLException {
        String insert = String.format("insert into contact(first_name, last_name, mail) values('%s', '%s', '%s')", contato.getFirstName(), contato.getLastName(), contato.getMail());
        state.execute(insert);
        System.out.printf("Usuário %s inserido com sucesso%n", contato.getFirstName());
    }

    public void insertPhones(Statement state, Phone phone) throws SQLException {
        String insert = String.format("insert into phones(phone) values('%s')", phone.getNumero());
        state.execute(insert);
        System.out.printf("Telefone %s inserido com sucesso%n", phone.getNumero());
    }

    public void insertGroups(Statement state, Groups groups) throws SQLException {
        String insert = String.format("insert into groups(description) values('%s')", groups.getDescricao());
        state.execute(insert);
        System.out.printf("Grupo %s inserido com sucesso%n", groups.getDescricao());
    }

    public List<Contato> findAllContact(Statement state) throws SQLException {
        ResultSet resultSet = state.executeQuery("select * from contact;");
        List<Contato> contatos = new ArrayList<>();
        while (resultSet.next()) {
            Contato contato = new Contato();
            contato.setContactId(resultSet.getInt("contact_id"));
            contato.setFirstName(resultSet.getString("first_name"));
            contato.setLastName(resultSet.getString("last_name"));
            contato.setMail(resultSet.getString("mail"));
            contatos.add(contato);
        }
        return contatos;
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

    public List<Phone> findAllPhones(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from phones");
        List<Phone> phones = new ArrayList<>();
        while (resultSet.next()) {
            Phone phone = new Phone();
            phone.setPhoneId(resultSet.getInt("phone_id"));
            phone.setNumero(resultSet.getString("phone"));
            phones.add(phone);
        }
        return phones;
    }
}
