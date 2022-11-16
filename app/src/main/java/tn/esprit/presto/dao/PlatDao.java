package tn.esprit.presto.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import tn.esprit.presto.entities.Plat;

import java.util.List;

@Dao
public interface PlatDao {
    @Insert
    void addPlat(Plat plat);

    @Update
    void updatePlat(Plat plat);

    @Query("delete from Plat where id=:id")
    void delete(int id);

    @Query("Select * from Plat")
    List<Plat> getAllPlats();
}
