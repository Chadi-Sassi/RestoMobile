package esprit.tn.restomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.entities.Produit;
import esprit.tn.restomobile.entities.ProduitAdaptor;
import esprit.tn.restomobile.entities.Stock;
import esprit.tn.restomobile.entities.StockAdaptor;

public class ProduitActivity extends AppCompatActivity {
    ProduitAdaptor adapter;
    AppDataBase database;
    List<Produit> produits;
    Button addProduitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produit_main);
        database = AppDataBase.getAppDatabase(this);
        addProduitbtn = (Button) findViewById(R.id.AddProduitbtn);
        addProduitbtn.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), AddProduitActivity.class);
            startActivity(in);
        });
        produits = database.produitDao().getAll();
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.stockRv);
        adapter = new ProduitAdaptor(produits, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
