package esprit.tn.restomobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import esprit.tn.restomobile.database.AppDataBase;
import esprit.tn.restomobile.databinding.ActivityMainBinding;
import esprit.tn.restomobile.entities.Utilisateur;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPwd;
    //private AppBarConfiguration appBarConfiguration;
    //private ActivityMainBinding binding;
    private AppDataBase database ;
    boolean verif = true;
    //private UsersAdapter usersAdapter;
    Utilisateur user = new Utilisateur();
    Utilisateur userLogin = new Utilisateur();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        database = AppDataBase.getAppDatabase(this);
        user.setLastName("Farah"); user.setFirstName("Mohamed");user.setEmail("admin");user.setPassword("admin");user.setRole("ADMIN");
        database.userDao().insertOne(user);
        txtEmailLogin = (EditText) findViewById(R.id.EmployeeEmail);
        txtPwd = (EditText) findViewById(R.id.EmployeePassword);

        Button btn = (Button) findViewById(R.id.LoginAsSpecificEmployee);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmailLogin.getText().toString().matches("")) {
                    txtEmailLogin.setError("Email is required");
                    // Toast.makeText(context, "You did not enter a Email Address", Toast.LENGTH_SHORT).show();
                    verif = false;

                }
                if (txtPwd.getText().toString().matches("")) {
                    txtPwd.setError("Password is Reqiuired");
                    // Toast.makeText(context, "You did not enter a Password", Toast.LENGTH_SHORT).show();
                    verif = false;
                }

                if(verif){
                    Utilisateur userLogin = database.userDao().login(txtEmailLogin.getText().toString(),txtPwd.getText().toString());
                    if(userLogin.equals(null)){
                        txtPwd.setError("Password or Email incorrect");
                        //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        verif = false;
                    }else{
                        if(userLogin.getRole().equals("ADMIN")){
                            // Home admin page
                            adminHomePage();
                            System.out.println("Home admin page");
                        }else{
                            //user home page
                            System.out.println("user home page");
                        }
                    }
                }

            }
        });



    }


    public void adminHomePage(){
        Intent myIntent = new Intent(MainActivity.this, UserActivity.class);
        myIntent.putExtra("key", 1); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }


}