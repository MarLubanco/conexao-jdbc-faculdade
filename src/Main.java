import controller.Controller;
import model.Contato;
import model.Groups;
import model.Phone;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void connect() throws SQLException {
        Controller service = new Controller();
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        Statement state = service.connect(connection);
//        service.dropTables(state);

        service.createTables(state);
        System.out.println("Banco de dados limpo");
        System.out.println("Banco de dados conectado");
        while (true) {
            System.out.println("1 - Inserir Contato \n" +
                    "2 - Inserir Phone \n" +
                    "3 - Inserir Group");
            String opcao = scanner.next();
            switch (opcao){
                case "1":
                    System.out.print("Digite nome: ");
                    String nome = scanner.next();
                    System.out.print("Digite sobrenome:");
                    String sobrenome =scanner.next();
                    System.out.print("Digite o email: ");
                    String email =scanner.next();
                    Contato contatoNovo =  new Contato(nome, sobrenome, email);
                    service.insertContact(state, contatoNovo);
                    System.out.println("Contato inserido com sucesso!");
                    List<Contato> allContact = service.findAllContact(state);
                    allContact.forEach(contato -> System.out.println(contato.toString()));
                    break;
                case "2":
                    System.out.println("Digite telefone: ");
                    String telefone = scanner.next();
                    Phone phoneNovo =  new Phone(telefone);
                    service.insertPhones(state, phoneNovo);
                    service.findAllPhones(state).forEach(phone -> System.out.println(phone.toString()));
                    break;
                case "3":
                    System.out.println("Digite descrição do grupo: ");
                    String descricao = scanner.next();
                    Groups groupsNovo =  new Groups(descricao);
                    service.insertGroups(state, groupsNovo);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }
//        controller.createTables(state);
//        controller.insertContact(state, "thome", "marcelo", "asdalll");
//        controller.insertContact(state, "caraii", "marcelo", "asdalll");
//        controller.insertPhones(state, "99955566");
//        controller.insertGroups(state, "GRUPO LEGAL");
//        controller.findAllContact(state);
//        controller.findContactByName(state,"thome");
//        controller.findContactById(state,12);

    }
    public static  void main(String args[]) throws SQLException {
        connect();
    }
}

