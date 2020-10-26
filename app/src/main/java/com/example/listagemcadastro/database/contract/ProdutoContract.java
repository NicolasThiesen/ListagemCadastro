package com.example.listagemcadastro.database.contract;

import com.example.listagemcadastro.database.entity.ProdutoEntity;

public class ProdutoContract {

    public static final String criarTabela(){
        return "CREATE TABLE IF NOT EXISTS "+ ProdutoEntity.TABLE_NAME +" ("
                + ProdutoEntity._ID + " INTEGER PRIMARY KEY,"
                + ProdutoEntity.COLUMN_NAME_NOME + " TEXT,"
                + ProdutoEntity.COLUMN_NAME_VALOR + " REAL)";
    }
    public  static final String removerTabela(){
        return "DROP TABLE IN EXISTS "+ ProdutoEntity.TABLE_NAME;
    }
}
