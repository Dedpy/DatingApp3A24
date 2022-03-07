/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.jfoenix.controls.JFXRippler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import org.controlsfx.control.Notifications;
import static tn.edu.esprit.gui.LoginController.CurrentUser;
import tn.edu.esprit.model.Reservation;
import tn.edu.esprit.model.Restaurant;
import tn.edu.esprit.model.User;
import tn.edu.esprit.services.ServiceReservation;
import tn.edu.esprit.services.ServiceRestaurant;
import tn.edu.esprit.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class FormulaireReservationController implements Initializable {

    ServiceRestaurant sp1 = new ServiceRestaurant();
    private Restaurant p1;
    @FXML
    private AnchorPane basepane;
    @FXML
    private Pane mypane;
    private TextField Fidresto;
    @FXML
    private TextField Fnbrplace;
    @FXML
    private DatePicker disponibilite;

    @FXML
    private ImageView idimage;
    @FXML
    private TextField nomres;
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
      private boolean NoDate() {
        boolean test = false;
        System.out.println(ChronoUnit.DAYS.between(this.disponibilite.getValue(), this.disponibilite.getValue()));
        int b = (int) ChronoUnit.DAYS.between(LocalDate.now(), this.disponibilite.getValue());
        System.out.println("aaaaaaaaaa" + b);
        if (b < 0) {

            test = true;

        }
        return test;
    }
       private void notif(ActionEvent event) {
        Notifications n = Notifications.create().title("")
                .text("Date début doit étre supériur à date fin ou date début sépérieur au date d'aujourd'hui")
                .graphic(null)
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notification");
                    }
                });
        n.showWarning();}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            p1 = sp1.GetRestobyid(Restaurant.getId_courant());
        } catch (SQLException ex) {
            Logger.getLogger(FormulaireReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFXRippler ripper = new JFXRippler(mypane);
        ripper.setRipplerFill(Paint.valueOf("#ff0000"));
        ripper.setRipplerRadius(60);
        ripper.setMaskType(JFXRippler.RipplerMask.RECT);
        basepane.getChildren().add(ripper);

        nomres.setText(p1.getNom_restaurant());
        nomres.setEditable(false);

        String a = p1.getImage();
        System.out.println(a);
        System.out.println(p1.getImage());
        File file = new File(a);
        Image image1 = new Image(file.toURI().toString());
        idimage.setImage(image1);

    }

    @FXML
    private void insert(ActionEvent event) throws SQLException {
        p1 = sp1.GetRestobyid(Restaurant.getId_courant());

       ServiceReservation sp = new ServiceReservation();

        ServiceUser sp2 = new ServiceUser();

        //nomres.setText(p1.getNom_restaurant());
        User p2 = new User();

        p2.setPrenom_user(prenom.getText());
        p2.setTelephone_user(Integer.parseInt(numtel.getText()));

      Reservation p = new Reservation();

        p.setId_restaurant(p1.getId_restaurant());
        // p.setId_user(CurrentUser.getId_user());
        p.setId_user(1);

        //  p2.setNom_user(nomuser.getText());//nom cureetn user 
        p.setNbPlace_reservation(Integer.parseInt(Fnbrplace.getText()));
       Date Date_reservation = Date.valueOf(this.disponibilite.getValue());
       p.setDate_reservation(Date_reservation);

        if (Integer.parseInt(Fnbrplace.getText()) > p1.getNb_placeResto()) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Error alert");
            alert.setHeaderText("Le nombre de places n'est pas disponible pour aujourd'hui, veuillez voir une autre date ");
            alert.setContentText("Le nombre de places est inférieur au nombre de restaurant que vous choisissez");

            alert.showAndWait();

        }else if (NoDate()) {
  
            notif(event);}
         else 

if (disponibilite.equals("")) {
    System.out.println("hello");
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Error alert");
            alert.setHeaderText("Le nombre de places n'est pas disponible pour aujourd'hui, veuillez voir une autre date ");
            alert.setContentText("Le nombre de places est inférieur au nombre de restaurant que vous choisissez");

            alert.showAndWait();
   
      System.out.println("uyfgoopm");
       }  else {
            sp.ajouter(p);
            sp1.updateNbrPlace(p1, Integer.parseInt(Fnbrplace.getText()));

        }
        

    }    

}
