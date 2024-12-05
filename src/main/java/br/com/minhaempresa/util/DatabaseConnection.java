package br.com.minhaempresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:meubanco.db"; // Caminho do banco de dados
            conn = DriverManager.getConnection(url);
            // Verificar e criar as tabelas se necessário
            createTables(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        // Criar a tabela Fornecedor
        String sqlFornecedor = "CREATE TABLE IF NOT EXISTS Fornecedor (" +
                               "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               "nome TEXT, " +
                               "telefone TEXT, " +
                               "endereco TEXT)";
        stmt.executeUpdate(sqlFornecedor);

        // Criar a tabela Produto
        String sqlProduto = "CREATE TABLE IF NOT EXISTS Produto (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "id_fornecedor INTEGER, " +
                            "nome TEXT, " +
                            "preco REAL, " +
                            "validade TEXT, " +
                            "FOREIGN KEY(id_fornecedor) REFERENCES Fornecedor(id))";
        stmt.executeUpdate(sqlProduto);

        // Criar a tabela Identificacao
        String sqlIdentificacao = "CREATE TABLE IF NOT EXISTS Identificacao (" +
                                  "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  "nome TEXT, " +
                                  "descricao TEXT, " +
                                  "id_produto INTEGER, " +
                                  "FOREIGN KEY(id_produto) REFERENCES Produto(id))";
        stmt.executeUpdate(sqlIdentificacao);

        // Criar a tabela Filial
        String sqlFilial = "CREATE TABLE IF NOT EXISTS Filial (" +
                           "cnpj TEXT PRIMARY KEY, " +
                           "nome TEXT, " +
                           "telefone TEXT, " +
                           "endereco TEXT)";
        stmt.executeUpdate(sqlFilial);

        // Criar a tabela Estoque
        String sqlEstoque = "CREATE TABLE IF NOT EXISTS Estoque (" +
                            "id_produto INTEGER, " +
                            "cnpj_filial TEXT, " +
                            "quantidade INTEGER, " +
                            "PRIMARY KEY(id_produto, cnpj_filial), " +
                            "FOREIGN KEY(id_produto) REFERENCES Produto(id), " +
                            "FOREIGN KEY(cnpj_filial) REFERENCES Filial(cnpj))";
        stmt.executeUpdate(sqlEstoque);

        stmt.close();
    }
}
