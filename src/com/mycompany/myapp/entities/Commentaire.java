/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Commentaire {
    public int idcommentaire,idpublication,iduser,publicationId,nblikes;
    public String commentaire,datecommentaire,usernamep;

    public Commentaire() {
    }

    public Commentaire(int idcommentaire, int idpublication, int iduser, int publicationId, int nblikes, String commentaire, String datecommentaire, String usernamep) {
        this.idcommentaire = idcommentaire;
        this.idpublication = idpublication;
        this.iduser = iduser;
        this.publicationId = publicationId;
        this.nblikes = nblikes;
        this.commentaire = commentaire;
        this.datecommentaire = datecommentaire;
        this.usernamep = usernamep;
    }

    public Commentaire(int idpublication, int iduser, int publicationId, int nblikes, String commentaire, String datecommentaire, String usernamep) {
        this.idpublication = idpublication;
        this.iduser = iduser;
        this.publicationId = publicationId;
        this.nblikes = nblikes;
        this.commentaire = commentaire;
        this.datecommentaire = datecommentaire;
        this.usernamep = usernamep;
    }

    public Commentaire(String commentaire,int idpublication , int iduser) {
        this.commentaire = commentaire;
        this.idpublication = idpublication;
        this.iduser = iduser;
    }


    public int getIdcommentaire() {
        return idcommentaire;
    }

    public int getIdpublication() {
        return idpublication;
    }

    public int getIduser() {
        return iduser;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public int getNblikes() {
        return nblikes;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getDatecommentaire() {
        return datecommentaire;
    }

    public String getUsernamep() {
        return usernamep;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public void setIdpublication(int idpublication) {
        this.idpublication = idpublication;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public void setNblikes(int nblikes) {
        this.nblikes = nblikes;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDatecommentaire(String datecommentaire) {
        this.datecommentaire = datecommentaire;
    }

    public void setUsernamep(String usernamep) {
        this.usernamep = usernamep;
    }

 @Override
    public String toString() {
        return "commentaire {" + "id=" + idcommentaire + ", nomc=" + usernamep + ", date=" + datecommentaire + "iduser"+ iduser+ "}";
    }

}