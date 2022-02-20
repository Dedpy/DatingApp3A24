/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author BAZINFO
 */
public class Reservation {
   private int id_reservation;
   private Date date_reservation ;
   private int nbPlace_reservation;
   private int id_restaurant;
   private int id_user;
   private int status_supprimer1;

      
    public Reservation(int id_reservation, Date date_reservation, int nbPlace_reservation, int id_restaurant, int id_user) {
        this.id_reservation = id_reservation;
        this.date_reservation = date_reservation;
        this.nbPlace_reservation = nbPlace_reservation;
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        
    }
   
    public Reservation(int id_reservation, Date date_reservation, int nbPlace_reservation, int id_restaurant, int id_user ,int status_supprimer1) {
        this.id_reservation = id_reservation;
        this.date_reservation = date_reservation;
        this.nbPlace_reservation = nbPlace_reservation;
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.status_supprimer1 = status_supprimer1;
    }

    public Reservation(int id_reservation) {
      this.id_reservation = id_reservation;
    }

 

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public int getNbPlace_reservation() {
        return nbPlace_reservation;
    }

    public void setNbPlace_reservation(int nbPlace_reservation) {
        this.nbPlace_reservation = nbPlace_reservation;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getStatus_supprimer1() {
        return status_supprimer1;
    }

    public void setStatus_supprimer1(int status_supprimer1) {
        this.status_supprimer1 = status_supprimer1;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", date_reservation=" + date_reservation + ", nbPlace_reservation=" + nbPlace_reservation + ", id_restaurant=" + id_restaurant + ", id_user=" + id_user + ", status_supprimer1" + status_supprimer1 +'}';
    }
    
}
