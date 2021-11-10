package esprit.tn.restomobile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import esprit.tn.restomobile.entities.Utilisateur;

@Dao
public interface UtilisateurDao {
    @Insert
    void insertOne(Utilisateur user);
    @Delete
    void delete(Utilisateur user);
    @Update
    void updateUser(Utilisateur user);
    @Query("SELECT * FROM user_table")
    List<Utilisateur> getAll();
    @Query("SELECT * FROM user_table WHERE uid = :id")
    Utilisateur getOne(int id);
    @Query("SELECT * FROM user_table WHERE email = :email AND pasword = :password")
    Utilisateur login(String email , String password);
}
