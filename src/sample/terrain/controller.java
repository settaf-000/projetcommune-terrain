package sample.terrain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.conncection.DbConnect;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controller implements Initializable {

    @FXML
    private TableColumn<terrain, String> col_adress;
    @FXML
    private TableColumn<terrain, String> col_image;
    @FXML
    private TableColumn<terrain, Date> col_date;

    @FXML
    private TableColumn<terrain, Integer> col_id;

    @FXML
    private TableColumn<terrain, String> col_nom;

    @FXML
    private TableColumn<terrain, String> col_type;
    @FXML
    private TextField nomterrain;
    @FXML
    private TextField adress;
    @FXML
    private TextField imageterrain;
    @FXML
    private DatePicker datecreation;


    @FXML
    private ComboBox<String> typeterrain;
    @FXML
    private TableView<terrain> tableterrains;

    terrain terrain=null;
    ObservableList<terrain> listT;
    int index=-1;
    Connection conn =null;
    ResultSet rs=null;
    PreparedStatement pst =null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listc = FXCollections.observableArrayList("FOOTBALL","BASKETBALL","HANDBALL");
        typeterrain.setItems(listc);

        col_id.setCellValueFactory(new PropertyValueFactory<terrain,Integer>("id_terrain"));
        col_nom.setCellValueFactory(new PropertyValueFactory<terrain,String>("nom_terrain"));
        col_adress.setCellValueFactory(new PropertyValueFactory<terrain,String>("adress"));
        col_type.setCellValueFactory(new PropertyValueFactory<terrain,String>("type_terrain"));
        col_image.setCellValueFactory(new PropertyValueFactory<terrain,String>("image_terrain"));
        col_date.setCellValueFactory(new PropertyValueFactory<terrain, Date>("date_creation"));
        listT = DbConnect.getDataterrains();
        tableterrains.setItems(listT);

    }
    public void Add_users (){
        conn = DbConnect.getConnection();
        String sql = "insert into terrain (nom_terrain,adress,type_terrain,image_terrain,date_creation)values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, nomterrain.getText());
            pst.setString(2, adress.getText());
            pst.setString(3, typeterrain.toString());
            pst.setString(4, imageterrain.getText());
            pst.setString(5, String.valueOf(Date.valueOf(datecreation.getValue())));
            pst.execute();

            JOptionPane.showMessageDialog(null, "terrain Add succes");
            UpdateTable();
            vide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    public void getSelected (MouseEvent event){
        index = tableterrains.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        nomterrain.setText(col_nom.getCellData(index).toString());
        adress.setText(col_adress.getCellData(index).toString());

        datecreation.setValue(LocalDate.parse(col_date.getCellData(index).toString()));
        imageterrain.setText(col_image.getCellData(index).toString());
    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<terrain,Integer>("id_terrain"));
        col_nom.setCellValueFactory(new PropertyValueFactory<terrain,String>("nom_terrain"));
        col_adress.setCellValueFactory(new PropertyValueFactory<terrain,String>("adress"));
        col_type.setCellValueFactory(new PropertyValueFactory<terrain,String>("type_terrain"));
        col_image.setCellValueFactory(new PropertyValueFactory<terrain,String>("image_terrain"));
        col_date.setCellValueFactory(new PropertyValueFactory<terrain,Date>("date_creation"));
        listT = DbConnect.getDataterrains();
        tableterrains.setItems(listT);
    }

    public void Delete(){

        conn = DbConnect.getConnection();
        terrain = tableterrains.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM `terrain` WHERE id_terrain  ="+terrain.getId_terrain();

        try {
            pst = conn.prepareStatement(query);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            vide();
         UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

public void vide(){
        nomterrain.setText("");
        adress.setText("");
        imageterrain.setText("");
        datecreation.setValue(LocalDate.ofEpochDay(2020-12-12));
}


    public void Edit (){

        try {
            terrain = tableterrains.getSelectionModel().getSelectedItem();
            conn = DbConnect.getConnection();
            String value1 = nomterrain.getText();
            String value2 = adress.getText();
            String value3 = typeterrain.getValue();
            String value4 = imageterrain.getText();
            Date value5 = Date.valueOf(datecreation.getValue());
            String sql = "update terrain set nom_terrain= '"+value1+"',adress= '"+
                    value2+"',type_terrain= '"+value3+"',image_terrain= '"+value4+"',date_creation= '"+value5+"' where id_terrain="+ terrain.getId_terrain()
           ;
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            vide();
            //search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableterrains.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        nomterrain.setText(col_nom.getCellData(index).toString());
        adress.setText(col_adress.getCellData(index).toString());

        datecreation.setValue(LocalDate.parse(col_date.getCellData(index).toString()));
        imageterrain.setText(col_image.getCellData(index).toString());

    }
    private Stage stage;
    private Scene scene;
    private Parent root;
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
        Parent root = FXMLLoader.load(getClass().getResource("terrain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void reservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../reservation/reservation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

