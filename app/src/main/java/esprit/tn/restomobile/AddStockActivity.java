package esprit.tn.restomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.entities.Stock;
import esprit.tn.restomobile.entities.Utilisateur;

public class AddStockActivity extends AppCompatActivity {
    AppDataBase database;
    EditText desc;
    EditText capacity;
    Button btnAdd;
    Stock s = new Stock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_stock);
        database = AppDataBase.getAppDatabase(this);
        desc = (EditText) findViewById(R.id.Stockdesc);
        capacity = (EditText) findViewById(R.id.Stockcapacity);
        btnAdd = (Button) findViewById(R.id.AddStockButton);

        btnAdd.setOnClickListener(v -> {
            s.setDescription(desc.getText().toString());
            s.setCapacity(Integer.valueOf(capacity.getText().toString()));
            database.stockDao().insertOne(s);
            Intent myIntent = new Intent(AddStockActivity.this, StockActivity.class);
            AddStockActivity.this.startActivity(myIntent);
        });
    }
}
