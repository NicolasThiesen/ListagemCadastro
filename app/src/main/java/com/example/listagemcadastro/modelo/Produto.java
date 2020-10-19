package com.example.listagemcadastro.modelo;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private Float valor;

    public Produto(int id, String nome, Float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return
                nome + "\n" + valor;
    }
}
