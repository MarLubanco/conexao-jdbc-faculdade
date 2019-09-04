package util;

public  class SqlConstaint {

    public static final String TABELA_CONTATO = "CREATE TABLE IF NOT EXISTS contact(" +
                " contact_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " first_name VARCHAR2(300) NOT NULL," +
                " last_name VARCHAR2(300) NOT NULL," +
                " mail VARCHAR2(300) NOT NULL);";

    public static final String TABELA_PHONES = "CREATE TABLE IF NOT EXISTS phones(" +
                " phone_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " phone VARCHAR2(300) NOT NULL);";

    public static final String TABELA_GROUPS = "CREATE TABLE IF NOT EXISTS groups(" +
                " group_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " description VARCHAR2(300) NOT NULL);";

    public static final String CONTACT_PHONES = "CREATE TABLE IF NOT EXISTS contact_phones (" +
                " contact_id integer (10)," +
                "phones_id integer(10)," +
                "FOREIGN KEY (contact_id) REFERENCES  contact(contact_id)," +
                "FOREIGN KEY (phones_id) REFERENCES  phones(phone_id));";

    public static final String CONTACT_GROUPS = "CREATE TABLE IF NOT EXISTS contact_groups (" +
                " contact_id integer (10)," +
                " groups_id integer(10)," +
                "FOREIGN KEY (contact_id) REFERENCES  contact(contact_id)," +
                "FOREIGN KEY (groups_id) REFERENCES  phones(group_id));";

    public static final String DROP_TABLE_CONTACT = "DROP TABLE contact ";
    public static final String DROP_TABLE_PHONE = "DROP TABLE phones ";
    public static final String DROP_TABLE_GROUP = "DROP TABLE groups ";
}
