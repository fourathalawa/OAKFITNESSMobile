/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Reclamation {
public int idreclamation,iduserreclamation,idcommentreclam,idreclameur;
public String descrreclam,categreclam,datereclam,etatreclamation,commentairerec,pubrec;

    public Reclamation(String descrreclam, String commentairerec,int idcommentreclam) {
        this.descrreclam = descrreclam;
        this.commentairerec = commentairerec;
        this.idcommentreclam = idcommentreclam;

    }

    public Reclamation(int idreclamation, int iduserreclamation, int idcommentreclam, int idreclameur, String descrreclam, String categreclam, String datereclam, String etatreclamation, String commentairerec, String pubrec) {
        this.idcommentreclam = idcommentreclam;
        this.descrreclam = descrreclam;
        this.commentairerec = commentairerec;
    }

  

    public Reclamation() {
    }

    public void setIdreclamation(int idreclamation) {
        this.idreclamation = idreclamation;
    }

    public void setIduserreclamation(int iduserreclamation) {
        this.iduserreclamation = iduserreclamation;
    }

    public void setIdcommentreclam(int idcommentreclam) {
        this.idcommentreclam = idcommentreclam;
    }

    public void setIdreclameur(int idreclameur) {
        this.idreclameur = idreclameur;
    }

    public void setDescrreclam(String descrreclam) {
        this.descrreclam = descrreclam;
    }

    public void setCategreclam(String categreclam) {
        this.categreclam = categreclam;
    }

    public void setDatereclam(String datereclam) {
        this.datereclam = datereclam;
    }

    public void setEtatreclamation(String etatreclamation) {
        this.etatreclamation = etatreclamation;
    }

    public void setCommentairerec(String commentairerec) {
        this.commentairerec = commentairerec;
    }

    public void setPubrec(String pubrec) {
        this.pubrec = pubrec;
    }

    public int getIdreclamation() {
        return idreclamation;
    }

    public int getIduserreclamation() {
        return iduserreclamation;
    }

    public int getIdcommentreclam() {
        return idcommentreclam;
    }

    public int getIdreclameur() {
        return idreclameur;
    }

    public String getDescrreclam() {
        return descrreclam;
    }

    public String getCategreclam() {
        return categreclam;
    }

    public String getDatereclam() {
        return datereclam;
    }

    public String getEtatreclamation() {
        return etatreclamation;
    }

    public String getCommentairerec() {
        return commentairerec;
    }

    public String getPubrec() {
        return pubrec;
    }

     @Override
    public String toString() {
        return "description {" + "id=" + descrreclam + ", id=" + idreclamation + ", idcomm=" + idcommentreclam + "idc"+idcommentreclam+ "}";
    }
}
