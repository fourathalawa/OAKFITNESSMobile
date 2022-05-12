/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Task {

    private int idpublication,iduser;
    private String datepublication, publication, usernamep;

    public Task() {
    }

    public Task(String datepublication, String publication, String usernamep) {
        this.datepublication = datepublication;
        this.publication = publication;
        this.usernamep = usernamep;
    }

    public Task(int idpublication, int iduser, String datepublication, String publication, String usernamep) {
        this.idpublication = idpublication;
        this.iduser = iduser;
        this.datepublication = datepublication;
        this.publication = publication;
        this.usernamep = usernamep;
    }


    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

  

    public Task(String publication, int iduser) {
        this.publication = publication;
        this.iduser = iduser;
    }

    public int getIdpublication() {
        return idpublication;
    }

    public String getDatepublication() {
        return datepublication;
    }

    public String getPublication() {
        return publication;
    }

    public String getUsernamep() {
        return usernamep;
    }

    public void setIdpublication(int idpublication) {
        this.idpublication = idpublication;
    }

    public void setDatepublication(String datepublication) {
        this.datepublication = datepublication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public void setUsernamep(String usernamep) {
        this.usernamep = usernamep;
    }

    

    @Override
    public String toString() {
        return "publication {" + "id=" + datepublication + ", nomc=" + publication + ", descr=" + usernamep + ", idpub=" + idpublication + "iduser" + iduser + "}";
    }

}
