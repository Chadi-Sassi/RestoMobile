package esprit.tn.restomobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produit_table")
public class Produit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "qte")
    private int qte;
    @ColumnInfo(name = "disponible")
    private boolean disponible;
    @ColumnInfo(name = "prix")
    private float prix;
    @ColumnInfo(name = "label")
    private String label;
  //  private int idStock;


    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", qte=" + qte +
                ", disponible=" + disponible +
                ", prix=" + prix +
                ", label='" + label + '\'' +
                '}';
    }
}
