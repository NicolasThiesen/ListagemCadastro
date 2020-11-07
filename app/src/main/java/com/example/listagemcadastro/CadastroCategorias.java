package com.example.listagemcadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listagemcadastro.database.CategoriaDAO;
import com.example.listagemcadastro.database.ProdutoDAO;
import com.example.listagemcadastro.modelo.Categoria;
import com.example.listagemcadastro.modelo.Produto;

public class CadastroCategorias extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categorias);

        setTitle("Cadastro de Produto");

    }

    private void carregarCategoria(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() !=null && intent.getExtras().get("categoriaEdicao") !=null){
            Categoria categoria = (Categoria) intent.getExtras().get("categoriaEdicao");
            EditText etDescricao = findViewById(R.id.et_descricao);
            etDescricao.setText(categoria.getDescricao());
            id = categoria.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }
    public void onClickSalvar(View v){

        EditText Enome = findViewById(R.id.et_descricao);;

        String descricao = Enome.getText().toString();

        Categoria categoria = new Categoria(id,descricao);

        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvo = categoriaDAO.salvar(categoria);
        if (salvo){
            finish();
        }else {
            Toast.makeText(CadastroCategorias.this, "Erro ao salvar o produto", Toast.LENGTH_LONG).show();
        }

        finish();

    }
}