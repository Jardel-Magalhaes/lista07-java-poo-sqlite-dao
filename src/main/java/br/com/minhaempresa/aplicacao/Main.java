package src.main.java.br.com.minhaempresa.aplicacao;

import src.main.java.br.com.minhaempresa.dao.FornecedorDAO;
import src.main.java.br.com.minhaempresa.model.Fornecedor;

public class Main {
    public static void main(String[] args) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        // Adicionar fornecedor
        Fornecedor fornecedor = new Fornecedor(0, "Fornecedor 1", "123456789", "Rua 1");
        fornecedorDAO.adicionarFornecedor(fornecedor);

        // Listar fornecedores
        fornecedorDAO.listarFornecedores().forEach(f -> System.out.println(f.getNome()));
    }
}
