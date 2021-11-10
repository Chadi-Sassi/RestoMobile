package tn.esprit.resto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.resto.database.AppDataBase;
import tn.esprit.resto.entity.User;
import tn.esprit.resto.entity.UsersAdapter;

public class MainActivity extends AppCompatActivity {
    EditText mFirstName;
    EditText mLastName;

    Button button2;
    Button addbtn;
    Button showbtn;

    private AppDataBase database ;
    RecyclerView userRv;
    private UsersAdapter usersAdapter;
    private List<User> userList = new ArrayList<User>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDataBase.getAppDatabase(this);
        userRv = findViewById(R.id.userRv);
     //   userList.add(new User("chadi", "sassi"));
     //   userList.add(new User("chadi", "sassi"));
    //    usersAdapter = new UsersAdapter(userList, this);


           userList = database.userDao().getAll();
           usersAdapter = new UsersAdapter(userList,getApplicationContext());

        addbtn = findViewById(R.id.addbtn);
        mFirstName = findViewById(R.id.mFirstName);
        mLastName = findViewById(R.id.mLastName);
        showbtn = findViewById(R.id.showbtn);
        showbtn.setOnClickListener(View->{
            Intent myIntent = new Intent(MainActivity.this, UpdateActivity.class);
           // myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });
        addbtn.setOnClickListener(view -> {
            User user = new User(mFirstName.getText().toString(),
                    mLastName.getText().toString());
            database.userDao().insertOne(user);
            Toast.makeText(this,"user added",Toast.LENGTH_SHORT).show();
            usersAdapter.notifyChange(database.userDao().getAll());

        });
/*
        btn_delete = findViewById(R.id.btn_delete);

        final User singleItem= userList.get(position);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            publicvoidonClick(Viewv) {
                AppDataBase.getAppDatabase(mContext).userDao().delete(singleItem);
                UserAdapter.this.notifyChange(AppDataBase.getAppDatabase(mContext).userDao().getAll());
            }
        });

*/






        userRv.setAdapter(usersAdapter);
        userRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));








       // usersAdapter.notifyChange(database.userDao().getAll());
    }




}