package src.main.java.br.com.minhaempresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Certifique-se de que o driver do SQLite está carregado
            Class.forName("org.sqlite.JDBC");
            // URL de conexão ao banco de dados SQLite
            String url = "jdbc:sqlite:meubanco.db"; // Caminho do seu banco de dados
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
