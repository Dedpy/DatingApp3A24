/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import tn.edu.esprit.model.Reservation;
import tn.edu.esprit.model.Restaurant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tn.edu.esprit.utils.MyDB;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import tn.edu.esprit.model.User;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author BAZINFO
 */
public class ServiceReservation implements IService<Reservation> {

    private final Connection cnx;
    public static final String ACCOUNT_SID = System.getenv("ACdd4694f7755efbb29c9f88a958d82a65");
    public static final String AUTH_TOKEN = System.getenv("8d3245f599c43b76162cedbcd8636ca2");

    public ServiceReservation() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reservation p1) {
        try {
            String querry = "INSERT INTO `reservation` (`id_reservation`,`date_reservation`,`nbPlace_reservation`,`id_restaurant`,`id_user`,`archive`) VALUES('" + p1.getId_reservation() + "','" + p1.getDate_reservation() + "','" + p1.getNbPlace_reservation() + "','" + p1.getId_restaurant() + "','" + p1.getId_user() + "','" + p1.getArchive() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(querry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /*    public void ajouter(Reservation p1) {
        try {
            String querry = "INSERT INTO `reservation` (`id_restaurant`,`nbPlace_reservation`,`date_reservation`) VALUES('" + p1.getId_restaurant()+ "','" + p1.getNbPlace_reservation() + "','" + p1.getDate_reservation()+ "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(querry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }*/
    public void join() {
        try {
            String querry = "SELECT `non_user`,`prenom_user` from `user` U inner join `reseration` R ON U.id_user = R.id_user";
            Statement stm = cnx.createStatement();
            stm.executeQuery(querry);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Reservation> afficher() {
        List<Reservation> Reservation = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservation WHERE `archive` = 0 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation.add(new Reservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Reservation;
    }

    @Override
    public boolean modifer(Reservation p1) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nouveau place : ");
        String newPlace = sc.nextLine();
        /*       Scanner sc1 = new Scanner(System.in);
             System.out.println("Nouveau place : ");
           String newDate =sc1.nextLine();
            Scanner sc2 = new Scanner(System.in);
             System.out.println("Nouveau place : ");
           String newRestaurant =sc2.nextLine();
              Scanner sc3 = new Scanner(System.in);
             System.out.println("Nouveau place : ");
           String newSta =sc3.nextLine();*/

        //    UPDATE `reservation` SET `id_reservation` = '2', `date_reservation` = '2022-02-13', `id_restaurant` = '5', `id_user` = '2', `status_supprimer1` = '2' WHERE `reservation`.`id_reservation` = 1 AND `reservation`.`date_reservation` = '2022-02-14' AND `reservation`.`id_user` = 1; 
        try {
            String req = " UPDATE `reservation` SET `nbPlace_reservation` = '" + newPlace + "' WHERE `id_reservation` = '" + p1.getId_reservation() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean supprimer(Reservation p1) {
        /*   try {
            String querry = "DELETE FROM `restaurant` WHERE `id_restaurant` = '" + p.getId_restaurant()+ "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(querry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
        }*/
        try {
            String req = " UPDATE `reservation` SET `archive` = 1  WHERE `id_reservation` = '" + p1.getId_reservation() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public void search(Reservation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendSMS(User p) {
        Twilio.init("ACdd4694f7755efbb29c9f88a958d82a65", "8d3245f599c43b76162cedbcd8636ca2");
        Message message = Message.creator(new PhoneNumber("+21658658857"),
                new PhoneNumber("+18608544709"),
                "Nom: " + p.getNom_user() + " Numero: " + p.getTelephone_user() + " Email: " + p.getEmail_user()).create();

        System.out.println(message.getSid());
    }

    public List<Reservation> Tri() {
        Comparator<Reservation> comparator = Comparator.comparing(Reservation::getDate_reservation);
        List<Reservation> reserv = afficher();
        return reserv.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> rechercher(Reservation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
