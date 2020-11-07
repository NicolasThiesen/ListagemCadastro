package com.example.listagemcadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listagemcadastro.database.ProdutoDAO;
import com.example.listagemcadastro.modelo.Produto;

public class CadastroProduto extends AppCompatActivity {

    private  int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produto");

        carregarProduto();
    }
    private void carregarProduto(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() !=null && intent.getExtras().get("produtoEdicao") !=null){
            Produto produto = (Produto) intent.getExtras().get("produtoEdicao");
            EditText etNome = findViewById(R.id.et_descricao);
            EditText etValor = findViewById(R.id.et_valor);
            etNome.setText(produto.getNome());
            etValor.setText(String.valueOf(produto.getValor()));
            id = produto.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }
    public void onClickSalvar(View v){

        EditText Enome = findViewById(R.id.et_descricao);
        EditText Evalor = findViewById(R.id.et_valor);

        String nome = Enome.getText().toString();
        Float valor = Float.parseFloat(Evalor.getText().toString());

        Produto produto = new Produto(id,nome, valor);

        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        boolean salvo = produtoDAO.salvar(produto);
        if (salvo){
            finish();
        }else {
            Toast.makeText(CadastroProduto.this, "Erro ao salvar o produto", Toast.LENGTH_LONG).show();
        }

        finish();

    }
}