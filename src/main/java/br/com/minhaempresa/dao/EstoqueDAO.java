package src.main.java.br.com.minhaempresa.dao;

import src.main.java.br.com.minhaempresa.model.Estoque;
import src.main.java.br.com.minhaempresa.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    // Método para adicionar estoque
    public void adicionarEstoque(Estoque estoque) {
        String sql = "INSERT INTO Estoque(id_produto, cnpj_filial, quantidade) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getIdProduto());
            stmt.setString(2, estoque.getCnpjFilial());
            stmt.setInt(3, estoque.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar estoque
    public void atualizarEstoque(Estoque estoque) {
        String sql = "UPDATE Estoque SET quantidade = ? WHERE id_produto = ? AND cnpj_filial = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getIdProduto());
            stmt.setString(3, estoque.getCnpjFilial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir estoque
    public void excluirEstoque(int idProduto, String cnpjFilial) {
        String sql = "DELETE FROM Estoque WHERE id_produto = ? AND cnpj_filial = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.setString(2, cnpjFilial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar estoque
    public List<Estoque> listarEstoques() {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "SELECT * FROM Estoque";

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estoque estoque = new Estoque(
                        rs.getInt("id_produto"),
                        rs.getString("cnpj_filial"),
                        rs.getInt("quantidade"));
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estoques;
    }
}
