package sample.association;

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
    private TableColumn<association, Date> col_datec;

    @FXML
    private TableColumn<association, Date> col_dater;

    @FXML
    private TableColumn<association, String> col_description;

    @FXML
    private TableColumn<association, Integer> col_id;

    @FXML
    private TableColumn<association, String> col_logo;

    @FXML
    private TableColumn<association, Integer> col_nb;

    @FXML
    private TableColumn<association, String> col_nom;
    @FXML
    private TableView<association> tableassociations;
    @FXML
    private DatePicker datecreation;

    @FXML
    private DatePicker dernierereserv;

    @FXML
    private TextArea description;

    @FXML
    private TextField nbreserv;

    @FXML
    private TextField nomassociation;

    association association=null;
    ObservableList<association> list;
    int index=-1;
    Connection conn =null;
    ResultSet rs=null;
    PreparedStatement pst =null;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_id.setCellValueFactory(new PropertyValueFactory<association,Integer>("id_association"));
        col_logo.setCellValueFactory(new PropertyValueFactory<association,String>("logo_association"));
        col_nom.setCellValueFactory(new PropertyValueFactory<association,String>("nom_association"));
        col_description.setCellValueFactory(new PropertyValueFactory<association,String>("description"));
        col_datec.setCellValueFactory(new PropertyValueFactory<association,Date>("date_creation"));
        col_dater.setCellValueFactory(new PropertyValueFactory<association, Date>("derniere_reserv"));
        col_nb.setCellValueFactory(new PropertyValueFactory<association, Integer>("nb_reserv"));
        list= DbConnect.getDataassociations();
        tableassociations.setItems(list);
    }
    public void Add_associations (){
        conn = DbConnect.getConnection();
        String sql = "insert into association (nom_association,description,date_creation,derniere_reserv,nb_reserv)" +
                "values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, nomassociation.getText());
            pst.setString(2, description.getText());
            pst.setString(3, String.valueOf(Date.valueOf(datecreation.getValue())));
            pst.setString(4, String.valueOf(Date.valueOf(dernierereserv.getValue())));
            pst.setString(5,  nbreserv.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Association Add succes");
            UpdateTable();
            vide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void vide(){
        nomassociation.setText("");
        description.setText("");
        nbreserv.setText("");
        dernierereserv.setValue(LocalDate.ofEpochDay(2020-12-12));
        datecreation.setValue(LocalDate.ofEpochDay(2020-12-12));
    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<association,Integer>("id_association"));
        col_logo.setCellValueFactory(new PropertyValueFactory<association,String>("logo_association"));
        col_nom.setCellValueFactory(new PropertyValueFactory<association,String>("nom_association"));
        col_description.setCellValueFactory(new PropertyValueFactory<association,String>("description"));
        col_datec.setCellValueFactory(new PropertyValueFactory<association,Date>("date_creation"));
        col_dater.setCellValueFactory(new PropertyValueFactory<association, Date>("derniere_reserv"));
        col_nb.setCellValueFactory(new PropertyValueFactory<association, Integer>("nb_reserv"));
        list= DbConnect.getDataassociations();
        tableassociations.setItems(list);
    }
    public void Delete(){

        conn = DbConnect.getConnection();
        association = tableassociations.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM `association` WHERE id_association="+association.getId_association();

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
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableassociations.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        nomassociation.setText(col_nom.getCellData(index).toString());
        description.setText(col_description.getCellData(index).toString());
        dernierereserv.setValue(LocalDate.parse(col_dater.getCellData(index).toString()));
        datecreation.setValue(LocalDate.parse(col_datec.getCellData(index).toString()));
        nbreserv.setText(col_nb.getCellData(index).toString());

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
        root = FXMLLoader.load(getClass().getResource("association.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("../reservation/reservation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
