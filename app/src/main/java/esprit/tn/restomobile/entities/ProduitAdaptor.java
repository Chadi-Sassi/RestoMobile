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

import esprit.tn.restomobile.ProduitActivity;
import esprit.tn.restomobile.R;
import esprit.tn.restomobile.database.AppDataBase;

public class ProduitAdaptor extends RecyclerView.Adapter<ProduitAdaptor.ProduitViewHolder>{
    List<Produit> produits;
    Context context;

    private AppDataBase database ;
    RecyclerView stockRv;

    public ProduitAdaptor(List<Produit> produits, Context context) {
        this.produits = produits;
        this.context = context;
        database = AppDataBase.getAppDatabase(context);
    }

    @NonNull
    @Override
    public ProduitAdaptor.ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_business, parent, false);
        return new ProduitAdaptor.ProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitAdaptor.ProduitViewHolder holder, int position) {
        Produit p = produits.get(position);
        holder.label.setText(p.getLabel());
        holder.prix.setText(String.valueOf(p.getPrix()));
        holder.dispo.setText("true");
        holder.qte.setText(Integer.valueOf(p.getQte()));
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
            database.produitDao().delete(p);
            notifyChange(database.produitDao().getAll());

        });

    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public void notifyChange(List<Produit> all) {
        this.produits = produits;
        this.notifyDataSetChanged();
    }

    class ProduitViewHolder extends RecyclerView.ViewHolder{
        EditText label;
        EditText prix;
        EditText dispo;
        EditText qte;
        Button btnUpdate;
        Button btnDelete;
        public ProduitViewHolder(@NonNull View itemView) {
            super(itemView);
            label = (EditText) itemView.findViewById(R.id.ProduitLabel);
            qte = (EditText) itemView.findViewById(R.id.ProduitQte);
            prix = (EditText) itemView.findViewById(R.id.ProduitPrix);
            dispo = (EditText) itemView.findViewById(R.id.ProduitDisponible);
            btnUpdate = (Button) itemView.findViewById(R.id.UpdateProduitButton);
            btnDelete = (Button) itemView.findViewById(R.id.DeleteProduitButton);

            btnUpdate.setOnClickListener(v -> {
                Produit p = new Produit();
                p.setDisponible(true);
                p.setLabel(label.getText().toString());
                p.setQte(Integer.valueOf(qte.getText().toString()));
                p.setPrix(Float.valueOf(prix.getText().toString()));

                database.produitDao().updateOne(p);
                notifyItemChanged(p.getId());
            });

        }
    }
}
