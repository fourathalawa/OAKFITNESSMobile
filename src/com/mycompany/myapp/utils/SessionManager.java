/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.utils;

import com.codename1.io.Preferences;

/**
 *
 * @author User
 */
public class SessionManager {
     public static Preferences pref ; 
     private static int id ;
     private static String email ;
     private static String password;
 private static int role;
    public static String getDate() {
        return pref.get("date",date);
    }

    public static void setDate(String date) {
         pref.set("date",date);
    }

    public static long getTelephone() {
        return pref.get("telephone",telephone);
    }

    public static void setTelephone(long telephone) {
         pref.set("telephone",telephone);
    }
     private static String name ;
     private static String date ;
     private static long telephone;
    private static String lastname ;
    private static String profilepicture ;
     public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }
     public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }
     public static String getEmail() {
        return pref.get("email",email);
    }
public static int getRole() {
        return pref.get("role",role);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setRole(int role) {
        pref.set("role",role);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }
     public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
         pref.set("password",password);
    }
    public static String getName() {
        return pref.get("name",name);
    }

    public static void setName(String name) {
         pref.set("name",name);
    }
    
       
    public static String getLastname() {
        return pref.get("lastname",lastname);
    }

    public static void setLastname(String lastname) {
         pref.set("lastname",lastname);
    }
    
    
    
    public static String getProfilepicture() {
        return pref.get("profilepicture",profilepicture);
    }

    public static void setProfilepicture(String profilepicture) {
         pref.set("profilepicture",profilepicture);
    }

    public static String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
               
}
