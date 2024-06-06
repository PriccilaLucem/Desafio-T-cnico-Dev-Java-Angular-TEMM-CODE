package com.example.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "descricao", nullable=false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private double preco;

    @Column(name = "quantidade_em_estoque", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;


    public String getDescricao() {
        return descricao;
    }
    public Long getId() {
        return id;
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
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setId(Long id) {
        this.id = id;
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
