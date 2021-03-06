/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Heni Nechi
 */
public class Repas {

    private int IDRepas;
    private String PDej;
    private String Dej;
    private String Dinn;
    private int Calorie;
    private String RestOrActive;
    private String Image;

    public Repas() {

    }

    public Repas(int IDRepas, String PDej, String Dej, String Dinn, int Calorie, String RestOrActive,String Image) {
        this.IDRepas = IDRepas;
        this.PDej = PDej;
        this.Dej = Dej;
        this.Dinn = Dinn;
        this.Calorie = Calorie;
        this.RestOrActive = RestOrActive;
        this.Image = Image;
    }

    public Repas(String PDej, String Dej, String Dinn, int Calorie, String RestOrActive,String Image) {
        this.PDej = PDej;
        this.Dej = Dej;
        this.Dinn = Dinn;
        this.Calorie = Calorie;
        this.RestOrActive = RestOrActive;
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int Calorie) {
        this.Calorie = Calorie;
    }

    public int getIDRepas() {
        return IDRepas;
    }

    public void setIDRepas(int IDRepas) {
        this.IDRepas = IDRepas;
    }

    public String getPDej() {
        return PDej;
    }

    public void setPDej(String PDej) {
        this.PDej = PDej;
    }

    public String getDej() {
        return Dej;
    }

    public void setDej(String Dej) {
        this.Dej = Dej;
    }

    public String getDinn() {
        return Dinn;
    }

    public void setDinn(String Dinn) {
        this.Dinn = Dinn;
    }

    public String getRestOrActive() {
        return RestOrActive;
    }

    public void setRestOrActive(String RestOrActive) {
        this.RestOrActive = RestOrActive;
    }

    @Override
    public String toString() {
        return "Breakfest : " + PDej + ", Lunch : " + Dej + ", Dinner :" + Dinn + "";
    }

}
