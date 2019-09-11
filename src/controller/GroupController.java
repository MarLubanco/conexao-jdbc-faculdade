package controller;

import model.Groups;
import model.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupController {

    public void insertGroups(Statement state, Groups groups) throws SQLException {
        String insert = String.format("insert into groups(description) values('%s')", groups.getDescricao());
        state.execute(insert);
        System.out.printf("Grupo %s inserido com sucesso%n", groups.getDescricao());
    }

    public List<Groups> findAllGroups(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from groups");
        List<Groups> allGroups = new ArrayList<>();
        while (resultSet.next()) {
            Groups groups = new Groups();
            groups.setGroupId(resultSet.getInt("group_id"));
            groups.setDescricao(resultSet.getString("description"));
            allGroups.add(groups);
        }
        return allGroups;
    }
}
