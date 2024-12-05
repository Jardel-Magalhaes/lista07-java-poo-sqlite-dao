package br.com.minhaempresa.aplicacao;

import br.com.minhaempresa.dao.FornecedorDAO;
import br.com.minhaempresa.dao.ProdutoDAO;
import br.com.minhaempresa.dao.EstoqueDAO;
import br.com.minhaempresa.model.Fornecedor;
import br.com.minhaempresa.model.Produto;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Classe principal da aplicação que interage com o usuário para gerenciar fornecedores, produtos e estoques.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Instanciar DAOs
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();

        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    // Adicionar Fornecedor
                    System.out.println("Digite os dados do fornecedor:");
                    System.out.print("ID: ");
                    int idFornecedor = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Nome: ");
                    String nomeFornecedor = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefoneFornecedor = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String enderecoFornecedor = scanner.nextLine();
                    Fornecedor fornecedor = new Fornecedor(idFornecedor, nomeFornecedor, telefoneFornecedor, enderecoFornecedor);
                    fornecedorDAO.adicionarFornecedor(fornecedor);
                    System.out.println("Fornecedor adicionado com sucesso!");
                    break;

                case 2:
                    // Atualizar Fornecedor
                    System.out.println("Digite os dados atualizados do fornecedor:");
                    System.out.print("ID: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Nome: ");
                    String nomeAtualizar = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefoneAtualizar = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String enderecoAtualizar = scanner.nextLine();
                    Fornecedor fornecedorAtualizado = new Fornecedor(idAtualizar, nomeAtualizar, telefoneAtualizar, enderecoAtualizar);
                    fornecedorDAO.atualizarFornecedor(fornecedorAtualizado);
                    System.out.println("Fornecedor atualizado com sucesso!");
                    break;

                case 3:
                    // Excluir Fornecedor
                    System.out.print("Digite o ID do fornecedor a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    fornecedorDAO.excluirFornecedor(idExcluir);
                    System.out.println("Fornecedor excluído com sucesso!");
                    break;

                case 4:
                    // Filtrar produtos em estoque por fornecedor
                    System.out.print("Digite o ID do fornecedor: ");
                    int idFornecedorFiltro = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    estoqueDAO.filtrarProdutosPorFornecedor(idFornecedorFiltro)
                            .forEach(produto -> System.out.println(produto));
                    break;

                case 5:
                    // Filtrar produtos em estoque por filial
                    System.out.print("Digite o CNPJ da filial: ");
                    String cnpjFilialFiltro = scanner.nextLine();
                    estoqueDAO.filtrarProdutosPorFilial(cnpjFilialFiltro)
                            .forEach(produto -> System.out.println(produto));
                    break;

                case 6:
                    // Listar detalhes dos produtos
                    System.out.println("Detalhes dos produtos em estoque:");
                    estoqueDAO.listarDetalhesProdutos()
                            .forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    /**
     * Exibe o menu de opções para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\n----- Menu -----");
        System.out.println("1. Adicionar Fornecedor");
        System.out.println("2. Atualizar Fornecedor");
        System.out.println("3. Excluir Fornecedor");
        System.out.println("4. Filtrar Produtos em Estoque por Fornecedor");
        System.out.println("5. Filtrar Produtos em Estoque por Filial");
        System.out.println("6. Apresentar Nome do Fornecedor, Nome do Produto, Nome da Filial e Quantidade em Estoque");
        System.out.println("0. Sair");
    }
}
