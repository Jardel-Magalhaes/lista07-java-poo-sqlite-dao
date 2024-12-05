package br.com.minhaempresa.model;

/**
 * Classe que representa um produto no sistema.
 */
public class Produto {

    private int id;
    private int idFornecedor;
    private String nome;
    private double preco;
    private String validade;

    /**
     * Construtor padrão.
     */
    public Produto() {
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param id           O identificador do produto.
     * @param idFornecedor O identificador do fornecedor.
     * @param nome         O nome do produto.
     * @param preco        O preço do produto.
     * @param validade     A validade do produto.
     */
    public Produto(int id, int idFornecedor, String nome, double preco, String validade) {
        this.id = id;
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
    }

    // Getters e Setters

    /**
     * Retorna o ID do produto.
     * 
     * @return ID do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     * 
     * @param id O ID do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o ID do fornecedor.
     * 
     * @return ID do fornecedor.
     */
    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * Define o ID do fornecedor.
     * 
     * @param idFornecedor O ID do fornecedor.
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return Nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * 
     * @param nome O nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     * 
     * @return Preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     * 
     * @param preco O preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a validade do produto.
     * 
     * @return Validade do produto.
     */
    public String getValidade() {
        return validade;
    }

    /**
     * Define a validade do produto.
     * 
     * @param validade A validade do produto.
     */
    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", idFornecedor=" + idFornecedor + ", nome=" + nome + ", preco=" + preco
                + ", validade=" + validade + "]";
    }
}
