package sample.reservation;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.conncection.DbConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    private TableColumn<reservation, Integer> col_idr;

    @FXML
    private TableColumn<reservation, String> col_logo;

    @FXML
    private TableColumn<reservation, String> col_terrain;
    @FXML
    private TableColumn<reservation, Date> col_datee;

    @FXML
    private TableColumn<reservation, Date> col_dates;
    @FXML
    private TableColumn<reservation, String> col_nom;
    @FXML
    private TableView<sample.reservation.reservation> tablereservations;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML

    reservation reservation=null;
    ObservableList<reservation> list;
    int index=-1;
    Connection conn =null;
    ResultSet rs=null;
    PreparedStatement pst =null;

    public controller() {
    }


    public void home(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void association(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../association/association.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void terrain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../terrain/terrain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void reservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            col_idr.setCellValueFactory(new PropertyValueFactory<reservation,Integer>("id_reserv"));
            col_logo.setCellValueFactory(new PropertyValueFactory<reservation,String>("logo_association"));
            col_nom.setCellValueFactory(new PropertyValueFactory<reservation,String>("nom_association"));
            col_terrain.setCellValueFactory(new PropertyValueFactory<reservation,String>("nom_terrain"));
            col_datee.setCellValueFactory(new PropertyValueFactory<reservation, Date>("date_entre"));
            col_dates.setCellValueFactory(new PropertyValueFactory<reservation, Date>("date_sortie"));

            list= DbConnect.getDatareservations();
            tablereservations.setItems(list);

    }
}
