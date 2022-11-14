package tn.esprit.presto.database;

import android.content.Context;

import androidx.room.*;

import tn.esprit.presto.dao.UserDao;
import tn.esprit.presto.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;
    public abstract UserDao userDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "presto_db")
                     .allowMainThreadQueries()
                     .build();
        }
        return instance;
    }

}
