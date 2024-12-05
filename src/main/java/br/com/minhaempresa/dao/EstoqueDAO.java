package br.com.minhaempresa.dao;

import br.com.minhaempresa.model.Produto;
import br.com.minhaempresa.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    // Outros métodos...

    /**
     * Filtra produtos em estoque por fornecedor.
     *
     * @param idFornecedor ID do fornecedor.
     * @return Lista de produtos fornecidos pelo fornecedor especificado.
     */
    public List<String> filtrarProdutosPorFornecedor(int idFornecedor) {
        String sql = "SELECT Produto.nome AS produto, Fornecedor.nome AS fornecedor "
                   + "FROM Produto "
                   + "JOIN Fornecedor ON Produto.id_fornecedor = Fornecedor.id "
                   + "WHERE Fornecedor.id = ?";

        List<String> produtos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                produtos.add(String.format("Produto: %s, Fornecedor: %s",
                        rs.getString("produto"),
                        rs.getString("fornecedor")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao filtrar produtos por fornecedor: " + e.getMessage());
        }

        return produtos;
    }

    /**
     * Filtra os produtos em estoque por uma filial específica.
     *
     * @param cnpjFilial o CNPJ da filial para filtrar.
     * @return uma lista de produtos disponíveis na filial.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    public List<Produto> filtrarProdutosPorFilial(String cnpjFilial) throws SQLException {
        String sql = "SELECT Produto.* "
                   + "FROM Estoque "
                   + "JOIN Produto ON Estoque.id_produto = Produto.id "
                   + "WHERE Estoque.cnpj_filial = ?";

        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cnpjFilial);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_fornecedor"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("validade")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }

    /**
     * Lista os detalhes de produtos em estoque.
     *
     * @return Lista de informações sobre produtos, fornecedores e filiais.
     */
    public List<String> listarDetalhesProdutos() {
        String sql = "SELECT Produto.nome AS produto, "
                   + "Fornecedor.nome AS fornecedor, "
                   + "Filial.nome AS filial, "
                   + "Estoque.quantidade AS quantidade "
                   + "FROM Estoque "
                   + "JOIN Produto ON Estoque.id_produto = Produto.id "
                   + "JOIN Fornecedor ON Produto.id_fornecedor = Fornecedor.id "
                   + "JOIN Filial ON Estoque.cnpj_filial = Filial.cnpj";

        List<String> detalhes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                detalhes.add(String.format("Produto: %s, Fornecedor: %s, Filial: %s, Quantidade: %d",
                        rs.getString("produto"),
                        rs.getString("fornecedor"),
                        rs.getString("filial"),
                        rs.getInt("quantidade")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar detalhes dos produtos: " + e.getMessage());
        }

        return detalhes;
    }
}
