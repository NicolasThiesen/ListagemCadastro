package com.example.listagemcadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listagemcadastro.database.ProdutoDAO;
import com.example.listagemcadastro.modelo.Produto;

import java.util.ArrayList;

public class Produtos extends AppCompatActivity {
    private ListView list_categories;
    private ArrayAdapter<Produto> adapterProdutos;


    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        setTitle("Produtos");

        list_categories = findViewById(R.id.products_list);
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        defineOnClickListenerListView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.categorias:
                Intent intent = new Intent(Produtos.this, Categorias.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(Produtos.this,android.R.layout.simple_list_item_1,produtoDAO.listar());
        list_categories.setAdapter(adapterProdutos);
    }

    private void defineOnClickListenerListView(){
        list_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto clickProduto = adapterProdutos.getItem(position);
                Intent intent = new Intent(Produtos.this, CadastroProduto.class);
                intent.putExtra("produtoEdicao", clickProduto);
                startActivity(intent);
            }
        });
        list_categories.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
                Produto produtoClicked = adapterProdutos.getItem(position);
               dialog(produtoClicked);
                return true;
            }
        });
    }

    public void dialog (final Produto produto){
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir Item")
                    .setMessage("Você deseja Excluir o Item?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
                            produtoDAO.excluir(produto);
                            finish();
                            startActivity(getIntent());

                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.create().show();
    }

    public void  onClickNovoProduto(View v){
        Intent intent = new Intent(Produtos.this, CadastroProduto.class);
        startActivity(intent);
    }

}