package esprit.tn.restomobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import esprit.tn.restomobile.entities.Stock;
import esprit.tn.restomobile.entities.Utilisateur;
@Dao
public interface StockDao {
    @Insert
    void insertOne(Stock stock);

    @Delete
    void delete(Stock stock);

    @Update
    void updateOne(Stock stock);

    @Query("SELECT * FROM stock_table")
    List<Stock> getAll();

    @Query("SELECT * FROM stock_table WHERE id = :id")
    Stock getOne(int id);
}

