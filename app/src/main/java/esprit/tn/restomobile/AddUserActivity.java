package esprit.tn.restomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.entities.Utilisateur;

public class AddUserActivity extends AppCompatActivity {
    AppDataBase database;
    EditText firstNametxt;
    EditText lastNametxt;
    EditText emailtxt;
    EditText passwordtxt;
    EditText roletxt;
    Button btnAdd;
    Utilisateur u = new Utilisateur();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        database = AppDataBase.getAppDatabase(this);
        firstNametxt = (EditText) findViewById(R.id.AddUserFirstName);
        lastNametxt = (EditText) findViewById(R.id.UserLastName);
        emailtxt = (EditText) findViewById(R.id.UserEmail);
        passwordtxt = (EditText) findViewById(R.id.UserPassword);
        roletxt = (EditText) findViewById(R.id.UserRole);
        btnAdd = (Button) findViewById(R.id.AddUserButton);

        btnAdd.setOnClickListener(v -> {
        u.setRole(roletxt.getText().toString());
        u.setPassword(passwordtxt.getText().toString());
        u.setEmail(emailtxt.getText().toString());
        u.setFirstName(firstNametxt.getText().toString());
        u.setLastName(lastNametxt.getText().toString());
            database.userDao().insertOne(u);
            Intent myIntent = new Intent(AddUserActivity.this, UserActivity.class);
            AddUserActivity.this.startActivity(myIntent);
        });
    }
}
