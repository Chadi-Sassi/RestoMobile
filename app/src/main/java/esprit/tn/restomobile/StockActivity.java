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
import esprit.tn.restomobile.entities.Stock;
import esprit.tn.restomobile.entities.StockAdaptor;
import esprit.tn.restomobile.entities.Utilisateur;
import esprit.tn.restomobile.entities.UtilisateurAdaptor;

public class StockActivity  extends AppCompatActivity {
    StockAdaptor adapter;
    AppDataBase database;
    List<Stock> stocks;
    Button addStocktn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_main);
        database = AppDataBase.getAppDatabase(this);
        addStocktn = (Button) findViewById(R.id.AddStockbtn);
        addStocktn.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), AddStockActivity.class);
            startActivity(in);
        });
        stocks = database.stockDao().getAll();
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.stockRv);
        adapter = new StockAdaptor(stocks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
