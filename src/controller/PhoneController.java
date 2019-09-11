package controller;

import model.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneController {

    public void insertPhones(Statement state, Phone phone) throws SQLException {
        String insert = String.format("insert into phones(phone) values('%s')", phone.getNumero());
        state.execute(insert);
        System.out.printf("Telefone %s inserido com sucesso%n", phone.getNumero());
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
