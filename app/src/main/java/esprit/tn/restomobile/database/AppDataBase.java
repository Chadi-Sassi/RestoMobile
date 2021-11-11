package esprit.tn.restomobile.database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import esprit.tn.restomobile.dao.ProduitDao;
import esprit.tn.restomobile.dao.StockDao;
import esprit.tn.restomobile.dao.UtilisateurDao;
import esprit.tn.restomobile.entities.Produit;
import esprit.tn.restomobile.entities.Stock;
import esprit.tn.restomobile.entities.Utilisateur;

@Database(entities = {Utilisateur.class , Stock.class , Produit.class}, version = 4, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract UtilisateurDao userDao();
    public abstract StockDao stockDao();
    public abstract ProduitDao produitDao();
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
