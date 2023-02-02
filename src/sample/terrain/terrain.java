package sample.terrain;

import java.util.Date;

public class terrain {

    int id_terrain;
    String image_terrain;
    String nom_terrain;
    Date date_creation;
    String adress;
    String type_terrain;

    public terrain(int id_terrain, String nom_terrain, String adress, String type_terrain, String image_terrain, Date date_creation) {
        this.id_terrain = id_terrain;
        this.nom_terrain = nom_terrain;
        this.adress = adress;
        this.type_terrain = type_terrain;
        this.image_terrain = image_terrain;
        this.date_creation = date_creation;
    }

    public  int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public String getNom_terrain() {
        return this.nom_terrain;
    }

    public void setNom_terrain(String nom_terrain) {
        this.nom_terrain = nom_terrain;
    }

    public String getImage_terrain() {
        return this.image_terrain;
    }

    public void setImage_terrain(String image_terrain) {
        this.image_terrain = image_terrain;
    }

    public Date getDate_creation() {
        return this.date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getType_terrain() {
        return this.type_terrain;
    }

    public void setType_terrain(String type_terrain) {
        this.type_terrain = type_terrain;
    }
}

