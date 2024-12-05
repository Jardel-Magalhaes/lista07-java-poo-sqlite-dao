package src.main.java.br.com.minhaempresa.model;

public class Estoque {
    private int idProduto;
    private String cnpjFilial;
    private int quantidade;

    public Estoque(int idProduto, String cnpjFilial, int quantidade) {
        this.idProduto = idProduto;
        this.cnpjFilial = cnpjFilial;
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getCnpjFilial() {
        return cnpjFilial;
    }

    public void setCnpjFilial(String cnpjFilial) {
        this.cnpjFilial = cnpjFilial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
