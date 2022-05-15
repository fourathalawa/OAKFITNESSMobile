/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.serviceproduit;
import java.util.ArrayList;
import com.mycompany.myapp.utils.statics;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.Label;
import com.codename1.io.URL;
import com.codename1.io.File;
import java.io.IOException;


/**
 *
 * @author kriaa
 */
public class Listproduitfront  extends Form {
  private EncodedImage enc;
    Image image;
    ImageViewer imgv;
    private String url = "http://127.0.0.1:8000/public/uploads/images";
public Listproduitfront(Form previous) {

        setTitle("Welcome to Our Shop");

        serviceproduit es = new serviceproduit();
        ArrayList<Produit> list = es.getAllProducts();




            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
            this.add(chercherContianer);

            for (Produit r : list) {

                Container c3 = new Container(BoxLayout.y());
               
     /*   String url = Statics.BASE_URL +"/"+"uploads/"+r.getLogo();*/

                SpanLabel cat = new SpanLabel("Poduct Name  :" + r.getNomProduit());
                SpanLabel cat0 = new SpanLabel("Category   :" + r.getCategProduit());
                SpanLabel cat1 = new SpanLabel("description  :" + r.getDescrProduit());
                SpanLabel cat2 = new SpanLabel("Price :" + r.getPrixProduit());
                SpanLabel cat3 = new SpanLabel("Availability :" + r.getIsAvailable());
                SpanLabel cat4 = new SpanLabel("Product Image :" + r.getImageProduit());
                 SpanLabel cat7 = new SpanLabel("");
            
             //image
            try {              
                enc=EncodedImage.create("/hhh.png");
            } catch (IOException ex) {
            }
            image=URLImage.createToStorage(enc,r.getImageProduit(),url+r.getImageProduit(),URLImage.RESIZE_SCALE).scaled(700, 300);
            imgv=new ImageViewer(image);
            add(imgv);
            //endimage
                
                c3.add(cat);
                c3.add(cat0);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
                c3.add(cat7);
             // design
                cat.getStyle().setBgColor(0xEA9999);
                cat.getStyle().setBgTransparency(255);
                cat0.getStyle().setBgColor(0xEA9999);
                cat0.getStyle().setBgTransparency(255);
                 cat1.getStyle().setBgColor(0xEA9999);
                cat1.getStyle().setBgTransparency(255);
                cat2.getStyle().setBgColor(0xEA9999);
                cat2.getStyle().setBgTransparency(255);
                 cat3.getStyle().setBgColor(0xEA9999);
                cat3.getStyle().setBgTransparency(255);   
                 cat4.getStyle().setBgColor(0xEA9999);
                cat4.getStyle().setBgTransparency(255);

               
                addAll(c3);
           
                 //integration sarra Panier
                Button panier = new Button("Add to cart");
                panier.addPointerPressedListener(l -> {
                   //appelle fonction sarra
                });
              
               c3.add(panier);
               //integration mehdi wishlist
                Button wishlist = new Button("Add to wishlist");
                wishlist.addPointerPressedListener(l -> {
                   //appelle fonction mehdi
                });
               c3.add(wishlist);
               
}
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> previous.showBack()); // Revenir vers l'interface précédente

        }

}

