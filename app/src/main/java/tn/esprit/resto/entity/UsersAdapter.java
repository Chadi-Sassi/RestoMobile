package tn.esprit.resto.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.resto.R;
import tn.esprit.resto.database.AppDataBase;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    List<User> users;
    Context context;

    private AppDataBase database ;
    RecyclerView userRv;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = users.get(position);
        holder.FirstName.setText(u.getFirstName());
        holder.LastName.setText(u.getLastName());

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, "Item clicked", Toast.LENGTH_LONG).show();
        });
        database = AppDataBase.getAppDatabase(context);

        final User singleItem= users.get(position);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.userDao().delete(singleItem);
                notifyChange(database.userDao().getAll());
                Toast.makeText(context, "Item deleted", Toast.LENGTH_LONG).show();
            }
        });

        final User user3= users.get(position);
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user3.setFirstName("chadi");
                database.userDao().update(user3);
                notifyChange(database.userDao().getAll());
                Toast.makeText(context, "Item updated", Toast.LENGTH_LONG).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void notifyChange(List<User> all) {
        this.users = users;
        this.notifyDataSetChanged();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView FirstName;
        TextView LastName;
        Button btn_delete;
        Button button2;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            FirstName = itemView.findViewById(R.id.FirstName);
            LastName = itemView.findViewById(R.id.LastName);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            button2 = itemView.findViewById(R.id.button2);
        }
    }
}
