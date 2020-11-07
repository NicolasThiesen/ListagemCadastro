package com.example.listagemcadastro.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.listagemcadastro.database.entity.CategoriaEntity;
import com.example.listagemcadastro.database.entity.ProdutoEntity;
import com.example.listagemcadastro.modelo.Categoria;
import com.example.listagemcadastro.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final String SQL_LISTAR_TODOS = "SELECT * FROM  " + CategoriaEntity.TABLE_NAME;
    private  DBGateway dbGateway;

    public CategoriaDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Categoria categoria){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriaEntity.COLUMN_NAME_DESCRICAO, categoria.getDescricao());
        if(categoria.getId()> 0){
            return dbGateway.getDatabase().update(CategoriaEntity.TABLE_NAME,contentValues, CategoriaEntity._ID + "=?", new String[]{String.valueOf(categoria.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(CategoriaEntity.TABLE_NAME,null,contentValues)> 0;
    }
    public List<Categoria> listar(){
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS,null);
        while ((cursor.moveToNext())){
            int id = cursor.getInt(cursor.getColumnIndex(CategoriaEntity._ID));
            String descricao = cursor.getString(cursor.getColumnIndex((CategoriaEntity.COLUMN_NAME_DESCRICAO)));
            categorias.add(new Categoria(id,descricao));
        }
        cursor.close();
        return categorias;
    }
    public void excluir(Categoria categoria){
        dbGateway.getDatabase().delete(CategoriaEntity.TABLE_NAME,CategoriaEntity._ID +"=?", new String[]{String.valueOf(categoria.getId())});
    }
}
