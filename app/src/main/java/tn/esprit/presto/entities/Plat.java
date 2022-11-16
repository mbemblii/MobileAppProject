package tn.esprit.presto.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Plat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String price;

    private String description;

    private int restoId;





    public Plat(int id, String name, String price, String description, int restoId) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.restoId = restoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRestoId() {
        return restoId;
    }

    public void setRestoId(int restoId) {
        this.restoId = restoId;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", restoId=" + restoId +
                '}';
    }
}
