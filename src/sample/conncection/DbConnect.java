package sample.conncection;

import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.association.association;
import sample.reservation.reservation;
import sample.terrain.terrain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbConnect {
    public static Connection connection;
    public static   Connection getConnection(){


        String dbName="commune";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
         System.out.println("GOOOOOOS");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("wwwwwwwwwww");
        }


        return connection;
    }


    public static ObservableList<terrain> getDataterrains(){
        Connection conn = DbConnect.getConnection();
        ObservableList<terrain> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from terrain");
            ResultSet rs = ((PreparedStatement) ps).executeQuery();

            while (rs.next()){
                list.add(new terrain(Integer.parseInt(rs.getString("id_terrain")), rs.getString("nom_terrain"), rs.getString("adress"), rs.getString("type_terrain"), rs.getString("image_terrain"), rs.getDate("date_creation")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<association> getDataassociations(){
        Connection conn = DbConnect.getConnection();
        ObservableList<association> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from association");
            ResultSet rs = ((PreparedStatement) ps).executeQuery();

            while (rs.next()){
                list.add(new association(Integer.parseInt(rs.getString("id_association")), rs.getString("logo_association"), rs.getString("nom_association"),
                rs.getString("description"),rs.getDate("date_creation"), rs.getDate("derniere_reserv"),Integer.parseInt(rs.getString("nb_reserv"))));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<reservation> getDatareservations(){
        Connection conn = DbConnect.getConnection();
        ObservableList<reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from reservation");
            ResultSet rs = ((PreparedStatement) ps).executeQuery();

            while (rs.next()){
                list.add(new reservation(Integer.parseInt(rs.getString("id_reserv")), rs.getString("logo_association"), rs.getString("nom_association"),
                        rs.getString("nom_terrain"),rs.getDate("date_entre"), rs.getDate("date_sortie")));
            }
        } catch (Exception e) {
        }
        return list;
    }



}

