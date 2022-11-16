package tn.esprit.presto.database;

import tn.esprit.presto.entities.Plat;

public interface AdapterListener {
    void OnUpdate(Plat plat);
    void OnDelete(int id,int pos);
}
