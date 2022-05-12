/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Heni Nechi
 */
public class events {
    private int IDEvenement;
    private String IDCreatorEvenement;
    private Date DateEvenement;
    private String TitreEvenement;
    private String DescrEvenement;
    private String AdresseEvenement;
    private String TypeEvenement;
    private String Image;

    public events(String IDCreatorEvenement, Date DateEvenement, String TitreEvenement, String DescrEvenement, String AdresseEvenement, String TypeEvenement, String Image) {
        this.IDCreatorEvenement = IDCreatorEvenement;
        this.DateEvenement = DateEvenement;
        this.TitreEvenement = TitreEvenement;
        this.DescrEvenement = DescrEvenement;
        this.AdresseEvenement = AdresseEvenement;
        this.TypeEvenement = TypeEvenement;
        this.Image = Image;
    }

    public events() {

    }

    public events(int IDEvenement, String IDCreatorEvenement, Date DateEvenement, String TitreEvenement, String DescrEvenement, String AdresseEvenement, String TypeEvenement, String Image) {
        this.IDEvenement = IDEvenement;
        this.IDCreatorEvenement = IDCreatorEvenement;
        this.DateEvenement = DateEvenement;
        this.TitreEvenement = TitreEvenement;
        this.DescrEvenement = DescrEvenement;
        this.AdresseEvenement = AdresseEvenement;
        this.TypeEvenement = TypeEvenement;
        this.Image = Image;
    }
    

    public int getIDEvenement() {
        return IDEvenement;
    }

    public void setIDEvenement(int IDEvenement) {
        this.IDEvenement = IDEvenement;
    }

    public String getIDCreatorEvenement() {
        return IDCreatorEvenement;
    }

    public void setIDCreatorEvenement(String IDCreatorEvenement) {
        this.IDCreatorEvenement = IDCreatorEvenement;
    }

    public Date getDateEvenement() {
        return DateEvenement;
    }

    public void setDateEvenement(Date DateEvenement) {
        this.DateEvenement = DateEvenement;
    }

    public String getTitreEvenement() {
        return TitreEvenement;
    }

    public void setTitreEvenement(String TitreEvenement) {
        this.TitreEvenement = TitreEvenement;
    }

    public String getDescrEvenement() {
        return DescrEvenement;
    }

    public void setDescrEvenement(String DescrEvenement) {
        this.DescrEvenement = DescrEvenement;
    }

    public String getAdresseEvenement() {
        return AdresseEvenement;
    }

    public void setAdresseEvenement(String AdresseEvenement) {
        this.AdresseEvenement = AdresseEvenement;
    }

    public String getTypeEvenement() {
        return TypeEvenement;
    }

    public void setTypeEvenement(String TypeEvenement) {
        this.TypeEvenement = TypeEvenement;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "events{" + "IDEvenement=" + IDEvenement + ", IDCreatorEvenement=" + IDCreatorEvenement + ", DateEvenement=" + DateEvenement + ", TitreEvenement=" + TitreEvenement + ", DescrEvenement=" + DescrEvenement + ", AdresseEvenement=" + AdresseEvenement + ", TypeEvenement=" + TypeEvenement + ", Image=" + Image + '}';
    }
     

    
    
}
