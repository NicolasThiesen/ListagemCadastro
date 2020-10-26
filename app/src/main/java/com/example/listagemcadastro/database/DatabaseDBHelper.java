package com.example.listagemcadastro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.listagemcadastro.database.contract.ProdutoContract;

public class DatabaseDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_produto";
    private static final int DB_VERSION = 1;

    public DatabaseDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }
}
