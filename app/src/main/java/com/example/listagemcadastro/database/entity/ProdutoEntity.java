package com.example.listagemcadastro.database.entity;

import android.provider.BaseColumns;

public final class ProdutoEntity implements BaseColumns {

    public static final String TABLE_NAME = "produto";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_VALOR = "valor";

    private ProdutoEntity(){}


}
