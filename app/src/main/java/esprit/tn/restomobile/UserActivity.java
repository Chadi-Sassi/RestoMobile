package esprit.tn.restomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.List;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.entities.Utilisateur;
import esprit.tn.restomobile.entities.UtilisateurAdaptor;

public class UserActivity extends AppCompatActivity {
    UtilisateurAdaptor adapter;
    AppDataBase database;
    List<Utilisateur> users;
    Button stockBtn;
    Button addUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilisateur_main);
        database = AppDataBase.getAppDatabase(this);
        stockBtn = (Button) findViewById(R.id.Stockbtn);
        stockBtn.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), StockActivity.class);
            startActivity(in);
        });
        addUserBtn = (Button) findViewById(R.id.AddUserbtn);
        addUserBtn.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), AddUserActivity.class);
            startActivity(in);
        });
        users = database.userDao().getAll();
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.userRv);
        adapter = new UtilisateurAdaptor(users, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}