/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pidevv;
import entities.Produit;
import services.serviceproduit;
/**
 *
 * @author kriaa
 */
public class NewClass {
 public static void main(String[] args) {
        serviceproduit test = new serviceproduit();
        System.out.println(test.getAllProducts());
    }
}
