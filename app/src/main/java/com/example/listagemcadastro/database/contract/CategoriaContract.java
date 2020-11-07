package com.example.listagemcadastro.database.contract;

import com.example.listagemcadastro.database.entity.CategoriaEntity;

public class CategoriaContract {
    private CategoriaContract(){};

    public static final String criarTabela(){
        return "CREATE TABLE IF NOT EXISTS " + CategoriaEntity.TABLE_NAME + " (" +
                CategoriaEntity._ID + " INTEGER PRIMARY KEY," +
                CategoriaEntity.COLUMN_NAME_DESCRICAO + " TEXT)";
    }

    public static final String removerTabela() {
        return  "DROP TABLE IF EXIST " + CategoriaEntity.TABLE_NAME;
    }
}
