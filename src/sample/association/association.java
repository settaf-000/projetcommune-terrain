package sample.association;

import java.util.Date;

public class association {
    int id_association;
    String logo_association;
    String nom_association;

    String description;
    Date date_creation;
    Date derniere_reserv;
    int nb_reserv;




    public association(int id_association, String logo_association, String nom_association, String description, java.sql.Date date_creation, java.sql.Date derniere_reserv, int nb_reserv) {
        this.id_association = id_association;
        this.logo_association = logo_association;
        this.nom_association = nom_association;
        this.description = description;
        this.date_creation = date_creation;
        this.derniere_reserv = derniere_reserv;
        this.nb_reserv=nb_reserv;
    }

    public association(int id_reserv, String logo_association, String nom_association, String nom_terrain, java.sql.Date date_entre, java.sql.Date date_sortie) {
    }


    public int getId_association() {
        return id_association;
    }
    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public String getLogo_association() {
        return logo_association;
    }
    public void setLogo_association(String logo_association) {
        this.logo_association = logo_association;
    }

    public String getNom_association() {
        return nom_association;
    }
    public void setNom_association(String nom_association) {
        this.nom_association = nom_association;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }
    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDerniere_reserv() {
        return derniere_reserv;
    }
    public void setDerniere_reserv(Date derniere_reserv) {
        this.derniere_reserv = derniere_reserv;
    }

    public int getNb_reserv() {
        return nb_reserv;
    }

    public void setNb_reserv(int nb_reserv) {
        this.nb_reserv = nb_reserv;
    }
}
