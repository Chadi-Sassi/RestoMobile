package tn.esprit.resto.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.resto.entity.User;

@Dao
public interface UserDao {
    @Insert
    void insertOne(User user);
    @Delete
    void delete(User user);
    @Query("SELECT * FROM user_table")
    List<User> getAll();
    @Update
    void update(User user);

}
