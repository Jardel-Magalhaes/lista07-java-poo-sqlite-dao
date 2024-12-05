package br.com.minhaempresa.dao;

import br.com.minhaempresa.model.Identificacao;
import br.com.minhaempresa.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdentificacaoDAO {

    // Método para adicionar identificação
    public void adicionarIdentificacao(Identificacao identificacao) {
        String sql = "INSERT INTO Identificacao(nome, descricao, id_produto) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identificacao.getNome());
            stmt.setString(2, identificacao.getDescricao());
            stmt.setInt(3, identificacao.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar identificação
    public void atualizarIdentificacao(Identificacao identificacao) {
        String sql = "UPDATE Identificacao SET nome = ?, descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identificacao.getNome());
            stmt.setString(2, identificacao.getDescricao());
            stmt.setInt(3, identificacao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir identificação
    public void excluirIdentificacao(int id) {
        String sql = "DELETE FROM Identificacao WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar identificações
    public List<Identificacao> listarIdentificacoes() {
        List<Identificacao> identificacoes = new ArrayList<>();
        String sql = "SELECT * FROM Identificacao";

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Identificacao identificacao = new Identificacao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("id_produto"));
                identificacoes.add(identificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return identificacoes;
    }
}
