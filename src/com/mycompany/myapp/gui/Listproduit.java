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
/**
 *
 * @author kriaa
 */
public class Listproduit extends Form {
public Listproduit(Form previous) {

        setTitle("Liste des Produits");

        serviceproduit es = new serviceproduit();
        ArrayList<Produit> list = es.getAllProducts();

        

            Button ajoutproduit = new Button("Ajouter");

            ajoutproduit.addPointerPressedListener(l -> {
                new addProduit().show();
            });
    

            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
            chercherContianer.addAll(ajoutproduit);
            this.add(chercherContianer);

            for (Produit r : list) {

                Container c3 = new Container(BoxLayout.y());
     /*   String url = Statics.BASE_URL +"/"+"uploads/"+r.getLogo();*/

                SpanLabel cat = new SpanLabel("Nom du produit  :" + r.getNomProduit());
                SpanLabel cat0 = new SpanLabel("Category   :" + r.getCategProduit());
                SpanLabel cat1 = new SpanLabel("description  :" + r.getDescrProduit());
                SpanLabel cat2 = new SpanLabel("Price :" + r.getPrixProduit());
                SpanLabel cat3 = new SpanLabel("Availability :" + r.getIsAvailable());
                SpanLabel cat4 = new SpanLabel("Product Image :" + r.getImageProduit());
                SpanLabel cat5 = new SpanLabel("stock :" + r.getStockProduit()); 
           ////////////////////////////////Metier-CHIFFRE-AFFAIRE/////////////////////////////////////
             float chiffreaffaire = r.getStockProduit() * r.getPrixProduit() ; 
              
             SpanLabel cat6 = new SpanLabel("Total Revenue of this Product :" + chiffreaffaire); 
             cat6.getStyle().setBgColor(0xefe0cb);
             cat6.getStyle().setBgTransparency(255);
             if(r.getStockProduit()< 10){String stockalert ="Product Stock is Weak <10";
              SpanLabel cat7 = new SpanLabel("Stock Alert  :" + stockalert); 
             cat7.getStyle().setBgColor(0xefe0cb);
             cat7.getStyle().setBgTransparency(255);
               c3.add(cat7);
                }
             if(r.getStockProduit()> 10){String stockalert ="Product Stock is Okay >10";
              SpanLabel cat7 = new SpanLabel("Stock Alert  :" + stockalert); 
             cat7.getStyle().setBgColor(0xefe0cb);
             cat7.getStyle().setBgTransparency(255);
               c3.add(cat7);
               }
            
          ////////////////////////////////////////////////////////////////////////////////////
            Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
          /*  URLImage urlim = URLImage.createToStorage(enc, r.getLogo(), url +"/"  + r.getImageProduit());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);*/
                c3.add(cat);
                c3.add(cat0);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
                c3.add(cat5);
                c3.add(cat6);
                

                Button Delete = new Button("Delete");
                c3.add(Delete);
                
                Button Modifier = new Button("Modifier");
                Modifier.addPointerPressedListener(l -> {
                    new updateProduit(r).show();

                });
                c3.add(Modifier);

                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce produit ?\nCette action est irréversible!");
                    alert.add(message);
                    Button ok = new Button("Confirmer");
                    Button cancel = new Button(new Command("Annuler"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            es.Delete(r.getIdProduit());
                            new Listproduit(previous).show();
                        
                            //alert.dispose();
                            refreshTheme();
                        }

                    }
                    );

                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                    new Listproduit(previous).show();

                });
                ajoutproduit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        new Listproduit(previous).show();
                        refreshTheme();
                    }

                });
                System.out.println("add Product");

                addAll(c3);
}
              

          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        }

    

}
