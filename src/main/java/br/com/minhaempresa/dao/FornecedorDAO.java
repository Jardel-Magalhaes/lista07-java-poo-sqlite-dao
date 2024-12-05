package br.com.minhaempresa.dao;

import br.com.minhaempresa.model.Fornecedor;
import br.com.minhaempresa.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    // Outros métodos...

    /**
     * Atualiza os dados de um fornecedor no banco de dados.
     *
     * @param fornecedor Fornecedor com os dados atualizados.
     */
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
            System.err.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

/**
 * Adiciona um fornecedor ao banco de dados.
 *
 * @param fornecedor o fornecedor a ser adicionado.
 * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
 */
public void adicionarFornecedor(Fornecedor fornecedor) throws SQLException {
    String sql = "INSERT INTO Fornecedor (id, nome, telefone, endereco) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, fornecedor.getId());
        pstmt.setString(2, fornecedor.getNome());
        pstmt.setString(3, fornecedor.getTelefone());
        pstmt.setString(4, fornecedor.getEndereco());
        pstmt.executeUpdate();
        System.out.println("Fornecedor adicionado com sucesso: " + fornecedor.getNome());
    }
}


/**
 * Exclui um fornecedor do banco de dados pelo ID.
 *
 * @param id o ID do fornecedor a ser excluído.
 * @throws SQLException se ocorrer um erro ao excluir o registro.
 */
public void excluirFornecedor(int id) throws SQLException {
    String sql = "DELETE FROM Fornecedor WHERE id = ?";
    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Fornecedor com ID " + id + " excluído com sucesso.");
    }
}


    /**
     * Filtra fornecedores cadastrados.
     *
     * @return Lista de fornecedores cadastrados.
     */
    public List<Fornecedor> listarFornecedores() {
        String sql = "SELECT * FROM Fornecedor";
        List<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("endereco")
                );
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }
}
