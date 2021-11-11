package esprit.tn.restomobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import esprit.tn.restomobile.entities.Produit;
import esprit.tn.restomobile.entities.Stock;
@Dao
public interface ProduitDao {
    @Insert
    void insertOne(Produit produit);

    @Delete
    void delete(Produit produit);

    @Update
    void updateOne(Produit produit);

    @Query("SELECT * FROM produit_table")
    List<Produit> getAll();

    @Query("SELECT * FROM produit_table WHERE id = :id")
    Produit getOne(int id);
}
