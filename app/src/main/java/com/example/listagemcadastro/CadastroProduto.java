package com.example.listagemcadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.listagemcadastro.modelo.Produto;

public class CadastroProduto extends AppCompatActivity {
    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_PRODUTO_EXCLUIDO = 11;

    private boolean edicao = false;
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
            EditText etNome = findViewById(R.id.et_nome);
            EditText etValor = findViewById(R.id.et_valor);
            etNome.setText(produto.getNome());
            etValor.setText(String.valueOf(produto.getValor()));
            id = produto.getId();
            edicao = true;
        }
    }
    public void onClickExcluir(View v){
        if(edicao){
            EditText Enome = findViewById(R.id.et_nome);
            EditText Evalor = findViewById(R.id.et_valor);

            String nome = Enome.getText().toString();
            Float valor = Float.parseFloat(Evalor.getText().toString());
            Intent intent = new Intent();

            Produto produto = new Produto(id,nome, valor);

            intent.putExtra("produtoExcluido",produto);
            setResult(RESULT_CODE_PRODUTO_EXCLUIDO,intent);
            finish();
        }
    }
    public void onClickVoltar(View v){
        finish();
    }
    public void onClickSalvar(View v){

        EditText Enome = findViewById(R.id.et_nome);
        EditText Evalor = findViewById(R.id.et_valor);

        String nome = Enome.getText().toString();
        Float valor = Float.parseFloat(Evalor.getText().toString());
        Intent intent = new Intent();

        Produto produto = new Produto(id,nome, valor);
        if (edicao){
            intent.putExtra("produtoEdicao", produto);
            setResult(RESULT_CODE_PRODUTO_EDITADO,intent);
        }else {
            intent.putExtra("novoProduto", produto);
            setResult(RESULT_CODE_NOVO_PRODUTO, intent);
        }
        finish();

    }
}