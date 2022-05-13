/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;


import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Fourat Halaoua
 */
public class User {

    private int id;
    private String Nom;
    private String Prenom;
    private String Mail;
    private long Telephone_Number;
    private String Date_Naissance;
    private int Role;
    private int Numero_Pack;
    private String Date_Commance;
    private String Experience;
    private String Diplome;
    private String Adresse_Salle_Sport;
    private long Matricule_Fiscale;
    private String Password;
    private String Image;
    private int code; 

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public long getTelephone_Number() {
        return Telephone_Number;
    }

    public void setTelephone_Number(long Telephone_Number) {
        this.Telephone_Number = Telephone_Number;
    }

    public String getDate_Naissance() {
        return Date_Naissance;
    }

    public void setDate_Naissance(String Date_Naissance) {
        this.Date_Naissance = Date_Naissance;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public int getNumero_Pack() {
        return Numero_Pack;
    }

    public void setNumero_Pack(int Numero_Pack) {
        this.Numero_Pack = Numero_Pack;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getDate_Commance() {
        return Date_Commance;
    }

    public void setDate_Commance(String Date_Commance) {
        this.Date_Commance = Date_Commance;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String Experience) {
        this.Experience = Experience;
    }

    public String getDiplome() {
        return Diplome;
    }

    public void setDiplome(String Diplome) {
        this.Diplome = Diplome;
    }

    public String getAdresse_Salle_Sport() {
        return Adresse_Salle_Sport;
    }

    public void setAdresse_Salle_Sport(String Adresse_Salle_Sport) {
        this.Adresse_Salle_Sport = Adresse_Salle_Sport;
    }

    public long getMatricule_Fiscale() {
        return Matricule_Fiscale;
    }

    public void setMatricule_Fiscale(long Matricule_Fiscale) {
        this.Matricule_Fiscale = Matricule_Fiscale;
    }

    public User() {
    }
//Admin
    public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, int Role,String Password,int code,String image) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Password=Password;
        this.Image = image;
    this.code=code;
    }

    //mobile
     public User(String Nom, String Prenom, String Mail,int role,String Password,String date, long Telephone_Number) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = date;
        this.Role = role;
        this.Password=Password;
        
    }
    public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, int Role,String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Password=Password;
    }
//Adherent
    public User(String Nom, String Prenom, String Mail, long Telephone_Number,String Date_Naissance, int Role, int Numero_Pack, String Date_Commance,String Password,int code,String image) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Numero_Pack = Numero_Pack;
        this.Date_Commance = Date_Commance;
        this.Password=Password;
        this.code=code;
this.Image = image;
    }
      public User(String Nom, String Prenom, String Mail, long Telephone_Number,String Date_Naissance, int Role, int Numero_Pack, String Date_Commance,String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Numero_Pack = Numero_Pack;
        this.Date_Commance = Date_Commance;
        this.Password=Password;

    }
//Coach
    public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, int Role, String Experience, String Diplome,String Password,int code,String image) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Experience = Experience;
        this.Diplome = Diplome;
        this.Password=Password;
        this.code=code;
this.Image = image;
    }
    public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, int Role, String Experience, String Diplome,String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Experience = Experience;
        this.Diplome = Diplome;
        this.Password=Password;

    }
//Responsable
      public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, long Matricule_Fiscale, int Role,String Password,int code,String image) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Matricule_Fiscale = Matricule_Fiscale;
        this.Password=Password;
        this.code=code;
        this.Image = image;
    }
 public User(String Nom, String Prenom, String Mail, long Telephone_Number, String Date_Naissance, long Matricule_Fiscale, int Role,String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Telephone_Number = Telephone_Number;
        this.Date_Naissance = Date_Naissance;
        this.Role = Role;
        this.Matricule_Fiscale = Matricule_Fiscale;
        this.Password=Password;

    }
    public User(String Mail, String Password) {
        this.Mail = Mail;
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "" + Nom + " " + Prenom + "";
    }

    
        public String encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
    }
      public String decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
    }
}
