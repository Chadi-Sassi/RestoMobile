package esprit.tn.restomobile.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import esprit.tn.restomobile.R;
import esprit.tn.restomobile.database.AppDataBase;

public class UtilisateurAdaptor extends RecyclerView.Adapter<UtilisateurAdaptor.UserViewHolder>{
    List<Utilisateur> users;
    Context context;

    private AppDataBase database ;
    RecyclerView userRv;

    public UtilisateurAdaptor(List<Utilisateur> users, Context context) {
        this.users = users;
        this.context = context;
        database = AppDataBase.getAppDatabase(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.utilisateur_business, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Utilisateur u = users.get(position);
        holder.emailtxt.setText(u.getEmail());
        holder.firstNametxt.setText(u.getFirstName());
        holder.lastNametxt.setText(u.getLastName());
        holder.passwordtxt.setText(u.getPassword());
        holder.roletxt.setText(u.getRole());
      /*  holder.btnUpdate.setOnClickListener(v -> {
            Utilisateur user = new Utilisateur();
            user.setRole(holder.roletxt.getText().toString());
            user.setPassword(holder.passwordtxt.getText().toString());
            user.setEmail(holder.emailtxt.getText().toString());
            user.setFirstName(holder.firstNametxt.getText().toString());
            user.setLastName(holder.lastNametxt.getText().toString());
            database.userDao().updateUser(user);
            notifyItemChanged(user.getUid());
        });*/
        holder.btnDelete.setOnClickListener(v -> {
            database.userDao().delete(u);
            notifyChange(database.userDao().getAll());

        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void notifyChange(List<Utilisateur> all) {
        this.users = users;
        this.notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        EditText firstNametxt;
        EditText lastNametxt;
        EditText emailtxt;
        EditText passwordtxt;
        EditText roletxt;
        Button btnDelete;
        Button btnUpdate;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNametxt = (EditText) itemView.findViewById(R.id.AddUserFirstName);
            lastNametxt = (EditText) itemView.findViewById(R.id.UserLastName);
            emailtxt = (EditText) itemView.findViewById(R.id.UserEmail);
            passwordtxt = (EditText) itemView.findViewById(R.id.UserPassword);
            roletxt = (EditText) itemView.findViewById(R.id.UserRole);
            btnDelete = (Button) itemView.findViewById(R.id.DeleteUserButton);
            btnUpdate = (Button) itemView.findViewById(R.id.UpdateUserButton);
            btnUpdate.setOnClickListener(v -> {
                Utilisateur user = new Utilisateur();
                user.setRole(roletxt.getText().toString());
                user.setPassword(passwordtxt.getText().toString());
                user.setEmail(emailtxt.getText().toString());
                user.setFirstName(firstNametxt.getText().toString());
                user.setLastName(lastNametxt.getText().toString());
                database.userDao().updateUser(user);
                notifyItemChanged(user.getUid());
            });

        }
    }

}
