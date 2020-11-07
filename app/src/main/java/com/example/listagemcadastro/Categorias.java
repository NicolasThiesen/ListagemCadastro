package com.example.listagemcadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listagemcadastro.database.CategoriaDAO;
import com.example.listagemcadastro.database.ProdutoDAO;
import com.example.listagemcadastro.modelo.Categoria;
import com.example.listagemcadastro.modelo.Produto;

import java.util.ArrayList;

public class Categorias extends AppCompatActivity {
    private ListView list_categories;
    private ArrayAdapter<Categoria> adapterCategorias;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        setTitle("Categorias");

        list_categories = findViewById(R.id.categorias_list);

        ArrayList<Categoria> categoria = new ArrayList<Categoria>();

        defineOnClickListenerListView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.produtos:
                Intent intent = new Intent(Categorias.this, Produtos.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        adapterCategorias = new ArrayAdapter<Categoria>(Categorias.this,android.R.layout.simple_list_item_1,categoriaDAO.listar());
        list_categories.setAdapter(adapterCategorias);
    }

    private void defineOnClickListenerListView(){
        list_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categoria clickCategoria = adapterCategorias.getItem(position);
                Intent intent = new Intent(Categorias.this, CadastroCategorias.class);
                intent.putExtra("categoriaEdicao", (Parcelable) clickCategoria);
                startActivity(intent);
            }
        });
        list_categories.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
                Categoria categoriaClicked = adapterCategorias.getItem(position);
                dialog(categoriaClicked);
                return true;
            }
        });
    }

    public void dialog (final Categoria categoria){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Item")
                .setMessage("Você deseja Excluir o Item?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
                        categoriaDAO.excluir(categoria);
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

    public void  onClickNovaCategoria(View v){
        Intent intent = new Intent(Categorias.this, CadastroCategorias.class);
        startActivity(intent);
    }
}