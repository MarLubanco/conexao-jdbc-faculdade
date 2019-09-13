import controller.ContactController;
import controller.GroupController;
import controller.PhoneController;
import model.Contato;
import model.Groups;
import model.Phone;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void connect() throws SQLException {
        ContactController service = new ContactController();
        GroupController groupController = new GroupController();
        PhoneController phoneController = new PhoneController();
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        Statement state = service.connect(connection);
        service.dropTables(state);

        service.createTables(state);
        System.out.println("Banco de dados limpo");
        System.out.println("Banco de dados conectado");
        while (true) {
            System.out.println("1 - Inserir Contato \n" +
                    "2 - Inserir Phone \n" +
                    "3 - Inserir Group \n" +
                    "4 - Pesquisar contato por nome \n" +
                    "5 - Pesquisar contato por id");
            String opcao = scanner.next();
            switch (opcao){
                case "1":
                    System.out.print("Digite nome: ");
                    String nome = scanner.next();
                    System.out.print("Digite sobrenome:");
                    String sobrenome =scanner.next();
                    System.out.print("Digite o email: ");
                    String email =scanner.next();
                    System.out.println("Digite telefone: ");
                    String telefone = scanner.next();
                    Phone phoneNovo =  new Phone(telefone);
                    phoneController.insertPhones(state, phoneNovo);
                    phoneController.findAllPhones(state).forEach(phone -> System.out.println(phone.toString()));
                    Contato contatoNovo =  new Contato(nome, sobrenome, email);
                    service.insertContact(state, contatoNovo);
                    service.insertContactPhone(state, contatoNovo, phoneNovo);
                    System.out.println("Contato inserido com sucesso!");
                    List<Contato> allContact = service.findAllContact(state);
                    allContact.forEach(contato -> System.out.println(contato.toString()));
                    System.out.println("Digite descrição do grupo: ");
                    String descricao = scanner.next();
                    Groups groupsNovo = new Groups(descricao);
                    groupController.insertGroups(state, groupsNovo);
                    service.insertContactGroup(state, contatoNovo, groupsNovo);
                    break;
                case "2":
                    System.out.println("Digite telefone: ");
                    String tel = scanner.next();
                    Phone phone =  new Phone(tel);
                    phoneController.insertPhones(state, phone);
                    phoneController.findAllPhones(state).forEach(p -> System.out.println(p.toString()));
                    break;
                case "3":
                    System.out.println("Digite descrição do grupo: ");
                    String desc = scanner.next();
                    Groups groups = new Groups(desc);
                    groupController.insertGroups(state, groups);
                    groupController.findAllGroups(state).forEach(group -> System.out.println(group.toString()));
                    break;
                case "4":
                    System.out.println("Digite o nome para pesquisar: ");
                    String nomePesquisa = scanner.next();
                    service.findContactByName(state, nomePesquisa);
                    break;
                case "5":
                    System.out.println("Digite o id do contato pesquisar: ");
                    int id = scanner.nextInt();
                    service.findContactById(state, id);
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }
    }

    public static  void main(String args[]) throws SQLException {
        connect();
    }
}

