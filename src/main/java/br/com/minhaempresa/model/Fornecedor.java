package br.com.minhaempresa.model;

/**
 * Classe que representa um fornecedor no sistema.
 */
public class Fornecedor {

    private int id;
    private String nome;
    private String telefone;
    private String endereco;

    /**
     * Construtor padrão.
     */
    public Fornecedor() {
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param id       O identificador do fornecedor.
     * @param nome     O nome do fornecedor.
     * @param telefone O telefone do fornecedor.
     * @param endereco O endereço do fornecedor.
     */
    public Fornecedor(int id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters

    /**
     * Retorna o ID do fornecedor.
     * 
     * @return ID do fornecedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do fornecedor.
     * 
     * @param id O ID do fornecedor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do fornecedor.
     * 
     * @return Nome do fornecedor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do fornecedor.
     * 
     * @param nome O nome do fornecedor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone do fornecedor.
     * 
     * @return Telefone do fornecedor.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do fornecedor.
     * 
     * @param telefone O telefone do fornecedor.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o endereço do fornecedor.
     * 
     * @return Endereço do fornecedor.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do fornecedor.
     * 
     * @param endereco O endereço do fornecedor.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Fornecedor [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + "]";
    }
}
