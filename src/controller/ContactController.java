package controller;

import model.Contato;
import model.Phone;
import util.SqlConstaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactController {

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

    public void insertContactPhone(Statement state, Contato contato, Phone phone) throws SQLException {
        ResultSet resultSetPhone = state.executeQuery(String.format("SELECT * FROM phones where phone ='%s'", phone.getNumero()));
        int phone_id = 0;
        while (resultSetPhone.next()) {
            phone_id = resultSetPhone.getInt("phone_id");
        }
        ResultSet resultSet = state.executeQuery(String.format("SELECT * FROM contact where first_name ='%s'", contato.getFirstName()));
        int contact_id = 0;
        while (resultSet.next()) {
            contact_id = resultSet.getInt("contact_id");
        }
        String insert = String.format("insert into contact_phones(contact_id, phones_id) values('%s', '%s')", contact_id, phone_id);
        state.execute(insert);
        System.out.printf("Usuário Telefone %s inserido com sucesso%n", contato.getFirstName());
    }

    public void insertContactGroup(Statement state, String contato, String groups) throws SQLException {
        ResultSet resultSetGrupo = state.executeQuery(String.format("SELECT * FROM groups where description ='%s'", groups));
        int groups_id = 0;
        while (resultSetGrupo.next()) {
            groups_id = resultSetGrupo.getInt("group_id");
        }
        ResultSet resultSet = state.executeQuery(String.format("SELECT * FROM contact where first_name ='%s'", contato));
        int contact_id = 0;
        while (resultSet.next()) {
            contact_id = resultSet.getInt("contact_id");
        }
        String insert = String.format("insert into contact_groups(contact_id, groups_id) values('%s', '%s')", contact_id, groups_id);
        state.execute(insert);
        System.out.printf("Usuário contato %s inserido com sucesso%n", contato);
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
            Contato contato = new Contato();
            contato.setContactId(resultSet.getInt("contact_id"));
            contato.setFirstName(resultSet.getString("first_name"));
            contato.setLastName(resultSet.getString("last_name"));
            contato.setMail(resultSet.getString("mail"));
            System.out.println(contato.toString());
        }
    }

    public void findContactByName(Statement state, String fname) throws SQLException {
        ResultSet resultSet = state.executeQuery(String.format("select * from contact where first_name = '%s' ;", fname));
        while (resultSet.next()) {
            Contato contato = new Contato();
            contato.setContactId(resultSet.getInt("contact_id"));
            contato.setFirstName(resultSet.getString("first_name"));
            contato.setLastName(resultSet.getString("last_name"));
            contato.setMail(resultSet.getString("mail"));
            System.out.println(contato.toString());
        }
    }


    public void linkarUserNumero(Statement state, String numero, String nomeContato) throws SQLException {
        ResultSet resultSet = state.executeQuery(String.format("select * from contact where first_name = '%s' ;", nomeContato));
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("contact_id");
        }
        ResultSet resultSetPhone = state.executeQuery(String.format("select * from groups where description = '%s'", numero));
        int group_id = 0;
        while (resultSet.next()) {
            group_id = resultSet.getInt("group_id");
        }

        String insert = String.format("insert into contact_phones(contact_id, phones_id) values('%s', '%s')", id, group_id);
        state.execute(insert);
        System.out.println("Usuário Grupo inserido com sucesso");
    }

    public void deletarContact(Statement statement, String email) throws SQLException {
       statement.execute(String.format("delete from contact where mail='%s'", email));
    }

    public void listarTodosContatosDoGrupo(Statement state, String grupoName) throws SQLException {
        ResultSet resultSet = state.executeQuery(String.format("select * from contact " +
                "inner join contact_groups on contact.contact_id = contact_groups.contact_id " +
                "inner join groups on groups.group_id = contact_groups.groups_id " +
                "where description = '%s'", grupoName));

        List<String> nomesContatos = new ArrayList<>();
        while (resultSet.next()) {
            nomesContatos.add(resultSet.getString("first_name"));
        }
        System.out.println("CONTATOS DO GRUPO - " + grupoName);
        System.out.println("__________________________________");
        nomesContatos.forEach(System.out::println);
    }


}
