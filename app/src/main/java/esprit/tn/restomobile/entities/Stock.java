package esprit.tn.restomobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "stock_table")
public class Stock {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "capacity")
    private int capacity;
    @ColumnInfo(name = "description")
    private String description;
    //private List<Produit> produits;

    public Stock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
*/
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}
