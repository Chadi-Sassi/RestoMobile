package esprit.tn.restomobile.database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import esprit.tn.restomobile.dao.UtilisateurDao;
import esprit.tn.restomobile.entities.Utilisateur;

@Database(entities = {Utilisateur.class}, version = 2, exportSchema = true)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract UtilisateurDao userDao();
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "room_test_db")

                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
