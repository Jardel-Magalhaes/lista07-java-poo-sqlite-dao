package src.main.java.br.com.minhaempresa.dao;

import src.main.java.br.com.minhaempresa.model.Fornecedor;
import src.main.java.br.com.minhaempresa.util.DatabaseConnection;
import src.main.java.br.com.minhaempresa.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    // Método para adicionar fornecedor
    public void adicionarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor(nome, telefone, endereco) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar fornecedor
    public void atualizarFornecedor(Fornecedor fornecedor) {
        String sql = "UPDATE Fornecedor SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEndereco());
            stmt.setInt(4, fornecedor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir fornecedor
    public void excluirFornecedor(int id) {
        String sql = "DELETE FROM Fornecedor WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar fornecedores
    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("endereco"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }

    // Método para filtrar produtos por fornecedor (Assumindo que você queira
    // filtrar produtos fornecidos por um fornecedor específico)
    public List<Produto> filtrarProdutosPorFornecedor(int idFornecedor) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto WHERE id_fornecedor = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);
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
