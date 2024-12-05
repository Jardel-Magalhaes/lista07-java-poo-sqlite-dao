package br.com.minhaempresa.dao;

import br.com.minhaempresa.model.Produto;
import br.com.minhaempresa.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de acesso a dados relacionados ao Produto.
 */
public class ProdutoDAO {

    /**
     * Adiciona um novo produto ao banco de dados.
     *
     * @param produto Objeto Produto a ser adicionado.
     * @return true se o produto for adicionado com sucesso, caso contrário false.
     */
    public boolean adicionarProduto(Produto produto) {
        String sql = "INSERT INTO Produto (id_fornecedor, nome, preco, validade) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, produto.getIdFornecedor());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getValidade());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Filtra produtos de acordo com a filial.
     *
     * @param cnpjFilial CNPJ da filial para filtrar os produtos.
     * @return Lista de produtos relacionados à filial.
     */
    public List<Produto> filtrarProdutosPorFilial(String cnpjFilial) {
        String sql = "SELECT p.id, p.nome, p.preco, p.validade " +
                     "FROM Produto p " +
                     "INNER JOIN Estoque e ON p.id = e.id_produto " +
                     "INNER JOIN Filial f ON e.cnpj_filial = f.cnpj " +
                     "WHERE f.cnpj = ?";
        
        List<Produto> produtos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cnpjFilial);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setValidade(rs.getString("validade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    /**
     * Apresenta o nome do fornecedor, nome do produto, nome da filial e quantidade em estoque.
     *
     * @return Lista de informações detalhadas sobre produtos e estoques.
     */
    public List<String> listarProdutosEstoque() {
        String sql = "SELECT f.nome AS fornecedor, p.nome AS produto, fi.nome AS filial, e.quantidade " +
                     "FROM Estoque e " +
                     "INNER JOIN Produto p ON e.id_produto = p.id " +
                     "INNER JOIN Filial fi ON e.cnpj_filial = fi.cnpj " +
                     "INNER JOIN Fornecedor f ON p.id_fornecedor = f.id";
        
        List<String> detalhes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String detalhe = "Fornecedor: " + rs.getString("fornecedor") +
                                 ", Produto: " + rs.getString("produto") +
                                 ", Filial: " + rs.getString("filial") +
                                 ", Quantidade: " + rs.getInt("quantidade");
                detalhes.add(detalhe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalhes;
    }
}
