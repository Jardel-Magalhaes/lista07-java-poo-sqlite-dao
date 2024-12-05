package src.main.java.br.com.minhaempresa.model;

public class Produto {
    private int id;
    private int idFornecedor;
    private String nome;
    private double preco;
    private String validade;

    // Construtores, getters e setters
    public Produto(int id, int idFornecedor, String nome, double preco, String validade) {
        this.id = id;
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
