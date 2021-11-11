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

public class StockAdaptor extends RecyclerView.Adapter<StockAdaptor.StockViewHolder>{
    List<Stock> stocks;
    Context context;

    private AppDataBase database ;
    RecyclerView stockRv;

    public StockAdaptor(List<Stock> stocks, Context context) {
        this.stocks = stocks;
        this.context = context;
        database = AppDataBase.getAppDatabase(context);
    }

    @NonNull
    @Override
    public StockAdaptor.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_business, parent, false);
        return new StockAdaptor.StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdaptor.StockViewHolder holder, int position) {
        Stock s = stocks.get(position);
        holder.desc.setText(s.getDescription());
        holder.capacity.setText(String.valueOf(s.getCapacity()));
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
            database.stockDao().delete(s);
            notifyChange(database.stockDao().getAll());

        });

    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public void notifyChange(List<Stock> all) {
        this.stocks = stocks;
        this.notifyDataSetChanged();
    }

    class StockViewHolder extends RecyclerView.ViewHolder{
        EditText desc;
        EditText capacity;
        Button btnDelete;
        Button btnUpdate;
        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = (EditText) itemView.findViewById(R.id.Stockdesc);
            capacity = (EditText) itemView.findViewById(R.id.Stockcapacity);
            btnDelete = (Button) itemView.findViewById(R.id.DeleteStockButton);
            btnUpdate = (Button) itemView.findViewById(R.id.UpdateStockButton);
            btnUpdate.setOnClickListener(v -> {
                Stock s = new Stock();
                s.setCapacity(Integer.valueOf(capacity.getText().toString()));
                s.setDescription(desc.getText().toString());
                database.stockDao().updateOne(s);
                notifyItemChanged(s.getId());
            });

        }
    }
}
