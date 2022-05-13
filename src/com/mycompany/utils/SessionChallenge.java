/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

import com.codename1.io.Preferences;

/**
 *
 * @author User
 */
public class SessionChallenge {
       public static Preferences pref ; 
     private static int id ;
     private static float poidinit ;
     private static float poidob;

   private static int id_user;

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        SessionChallenge.id_user = id_user;
    }
     private static float poidnv=0 ;
     private static String datedeb ;
          private static String datefin ;

     private static float taille;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionChallenge.pref = pref;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionChallenge.id = id;
    }

    public static float getPoidinit() {
        return poidinit;
    }

    public static void setPoidinit(float poidinit) {
        SessionChallenge.poidinit = poidinit;
    }

    public static float getPoidob() {
        return poidob;
    }

    public static void setPoidob(float poidob) {
        SessionChallenge.poidob = poidob;
    }

    public static float getPoidnv() {
        return poidnv;
    }

    public static void setPoidnv(float poidnv) {
        SessionChallenge.poidnv = poidnv;
    }

    public static String getDatedeb() {
        return datedeb;
    }

    public static void setDatedeb(String datedeb) {
        SessionChallenge.datedeb = datedeb;
    }

    public static String getDatefin() {
        return datefin;
    }

    public static void setDatefin(String datefin) {
        SessionChallenge.datefin = datefin;
    }

    public static float getTaille() {
        return taille;
    }

    public static void setTaille(float taille) {
        SessionChallenge.taille = taille;
    }
    
    
}
