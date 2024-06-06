package com.example.store.dto;

public class ProductCategoryDTO {
    private String nome;
    private String descricao;
    private double preco;
    private int quantity;
    private String categoryNome;

    public String getCategoryNome() {
        return categoryNome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setCategoryNome(String categoryNome) {
        this.categoryNome = categoryNome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
