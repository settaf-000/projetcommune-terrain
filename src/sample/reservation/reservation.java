package sample.reservation;
import java.util.Date;
public class reservation {
    int id_reserv;
    String logo_association,nom_association,nom_terrain;
  Date date_entre, date_sortie;
  public reservation(int id_reserv,String logo_association,String nom_association,String nom_terrain,
                     Date date_entre,Date date_sortie){
      this.id_reserv=id_reserv;
      this.logo_association=logo_association;
      this.nom_association=nom_association;
      this.nom_terrain=nom_terrain;
      this.date_entre=date_entre;
      this.date_sortie=date_sortie;
  }

    public int getId_reserv() {
        return id_reserv;
    }
    public void setId_reserv(int id_reserv) {
        this.id_reserv = id_reserv;
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
    public String getNom_terrain() {
        return nom_terrain;
    }
    public void setNom_terrain(String nom_terrain) {
        this.nom_terrain = nom_terrain;
    }
    public Date getDate_sortie() {
        return date_sortie;
    }
    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public Date getDate_entre() {
        return date_entre;
    }
    public void setDate_entre(Date date_entre) {
        this.date_entre = date_entre;
    }
}
