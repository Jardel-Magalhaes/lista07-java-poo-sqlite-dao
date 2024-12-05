package src.main.java.br.com.minhaempresa.dao;

import src.main.java.br.com.minhaempresa.model.Filial;
import src.main.java.br.com.minhaempresa.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilialDAO {

    // Método para adicionar filial
    public void adicionarFilial(Filial filial) {
        String sql = "INSERT INTO Filial(cnpj, nome, telefone, endereco) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filial.getCnpj());
            stmt.setString(2, filial.getNome());
            stmt.setString(3, filial.getTelefone());
            stmt.setString(4, filial.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar filial
    public void atualizarFilial(Filial filial) {
        String sql = "UPDATE Filial SET nome = ?, telefone = ?, endereco = ? WHERE cnpj = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getTelefone());
            stmt.setString(3, filial.getEndereco());
            stmt.setString(4, filial.getCnpj());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir filial
    public void excluirFilial(String cnpj) {
        String sql = "DELETE FROM Filial WHERE cnpj = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar filiais
    public List<Filial> listarFiliais() {
        List<Filial> filiais = new ArrayList<>();
        String sql = "SELECT * FROM Filial";

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Filial filial = new Filial(
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("endereco"));
                filiais.add(filial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filiais;
    }
}
