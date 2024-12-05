package src.main.java.br.com.minhaempresa.model;

public class Identificacao {
    private int id;
    private String nome;
    private String descricao;
    private int idProduto;

    public Identificacao(int id, String nome, String descricao, int idProduto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
}
