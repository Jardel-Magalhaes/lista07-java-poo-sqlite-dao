package src.main.java.br.com.minhaempresa.dao;

import src.main.java.br.com.minhaempresa.model.Produto;
import src.main.java.br.com.minhaempresa.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Método para adicionar produto
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO Produto(id_fornecedor, nome, preco, validade) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getIdFornecedor());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getValidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar produto
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE Produto SET nome = ?, preco = ?, validade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getValidade());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir produto
    public void excluirProduto(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("validade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para filtrar produtos em estoque por filial
    public List<Produto> filtrarProdutosPorFilial(String cnpjFilial) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.* FROM Produto p " +
                "JOIN Estoque e ON p.id = e.id_produto " +
                "WHERE e.cnpj_filial = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpjFilial);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("validade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
