package esprit.tn.restomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.entities.Produit;
import esprit.tn.restomobile.entities.Stock;

public class AddProduitActivity extends AppCompatActivity {
    AppDataBase database;
    EditText label;
    EditText prix;
    EditText dispo;
    EditText qte;
    Button btnAdd;
    Produit p = new Produit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_produit);
        database = AppDataBase.getAppDatabase(this);
        label = (EditText) findViewById(R.id.ProduitLabel);
        qte = (EditText) findViewById(R.id.ProduitQte);
        prix = (EditText) findViewById(R.id.ProduitPrix);
        dispo = (EditText) findViewById(R.id.ProduitDisponible);
        btnAdd = (Button) findViewById(R.id.AddProduitButton);

        btnAdd.setOnClickListener(v -> {
            p.setDisponible(true);
            p.setLabel(label.getText().toString());
            p.setQte(Integer.valueOf(qte.getText().toString()));
            p.setPrix(Float.valueOf(prix.getText().toString()));

            database.produitDao().insertOne(p);
            Intent myIntent = new Intent(AddProduitActivity.this, ProduitActivity.class);
            AddProduitActivity.this.startActivity(myIntent);
        });
    }
}
