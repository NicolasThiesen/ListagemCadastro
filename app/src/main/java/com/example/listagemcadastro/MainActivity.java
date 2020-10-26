package com.example.listagemcadastro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listagemcadastro.database.ProdutoDAO;
import com.example.listagemcadastro.modelo.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list_products;
    private ArrayAdapter<Produto> adapterProdutos;

    private final int REQUEST_CODE_NOVO_PRODUTO = 1;
    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int REQUEST_CODE_EDIT_PRODUTO = 2;
    private final int RESULT_CODE_EDIT_PRODUTO =11;
    private final int RESULT_CODE_EXCLUDE_PRODUTO =12;

    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");

        list_products = findViewById(R.id.products_list);
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        defineOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this,android.R.layout.simple_list_item_1,produtoDAO.listar());
        list_products.setAdapter(adapterProdutos);
    }

    private void defineOnClickListenerListView(){
        list_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto clickProduto = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProduto.class);
                intent.putExtra("produtoEdicao", clickProduto);
                startActivity(intent);
            }
        });
        list_products.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        Intent intent = new Intent(MainActivity.this, CadastroProduto.class);
        startActivity(intent);
    }

}