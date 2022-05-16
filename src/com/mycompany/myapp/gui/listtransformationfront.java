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
import com.mycompany.myapp.entities.Transformation;
import com.mycompany.myapp.services.servicetransformationfront;
import com.mycompany.myapp.utils.SessionManager;


import java.util.ArrayList;
import com.mycompany.myapp.utils.statics;
/**
 *
 * @author kriaa
 */
public class listtransformationfront extends Form {
public listtransformationfront(Form previous) {
   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> previous.showBack()); // Revenir vers l'interface précédente
        setTitle("Transformation Hub");

        servicetransformationfront es = new servicetransformationfront();
        ArrayList<Transformation> list = es.getAllTransformations();

        

            Button ajouttransformation = new Button("Add Transformation");

            ajouttransformation.addPointerPressedListener(l -> {
                new addTransformation().show();
            });

            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
            chercherContianer.addAll(ajouttransformation);
            this.add(chercherContianer);

            for (Transformation r : list) {

                Container c3 = new Container(BoxLayout.y());
     /*   String url = Statics.BASE_URL +"/"+"uploads/"+r.getLogo();*/

              
                SpanLabel cat = new SpanLabel("Transformation Title  :" + r.getTitreImage());
                SpanLabel cat0 = new SpanLabel("Descreption   :" + r.getDescreptionImage());
                SpanLabel cat1 = new SpanLabel("Before image  :" + r.getImageAvant());
                SpanLabel cat2 = new SpanLabel("After image :" + r.getImageApres());
                SpanLabel cat3 = new SpanLabel("Weight Before :" + r.getPoidAvant());
                SpanLabel cat4 = new SpanLabel("Weight After :" + r.getPoidApres());
                SpanLabel cat5 = new SpanLabel("Height Before :" + r.getTailleAvant()); 
                SpanLabel cat6 = new SpanLabel("Height After :" + r.getTailleApres());
                SpanLabel cat7 = new SpanLabel("Votes :" + r.getTlikes());
                SpanLabel cat8 = new SpanLabel("User ID :" + r.getIdUser()); 
         ///////////////////////////////IMC-Metier//////////////////////////////////////
             float imcavant = r.getPoidAvant() / (r.getTailleAvant() * r.getTailleAvant());
             float imcapres =  r.getPoidApres() / (r.getTailleApres() * r.getTailleApres());
             SpanLabel cat9 = new SpanLabel("Imc Before Transformation :" + imcavant);
             SpanLabel cat10 = new SpanLabel("Imc After Transformation :" + imcapres); 
             cat9.getStyle().setBgColor(0xefe0cb);
             cat9.getStyle().setBgTransparency(255);
             cat10.getStyle().setBgColor(0xefe0cb);
             cat10.getStyle().setBgTransparency(255);
          ///////////////////////////////////////////////////////////////////////////////
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
                c3.add(cat7);
                c3.add(cat8);
                c3.add(cat9);
                c3.add(cat10);
               
                Button Delete = new Button("Delete");
                
                
                Button Modifier = new Button("Modifier");
                Modifier.addPointerPressedListener(l -> {
                    new updateTransformation(r).show();
                });

                Button Upvote = new Button("Up Vote");
                Button Downvote = new Button("Down Vote");  
/// integration          
         if(SessionManager.getId()== r.getIdUser()){
                c3.add(Modifier);
                c3.add(Delete);}
                c3.add(Upvote);
                c3.add(Downvote);
//}-------------------------------------------------------
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Careful");
                    SpanLabel message = new SpanLabel("Are you sure you want to remove this Transformation ?\nThis action is irreversible!");
                    alert.add(message);
                    Button ok = new Button("Confirm");
                    Button cancel = new Button(new Command("Cancel"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            es.Delete(r.getIdImage());
                            new listtransformationfront(previous).show();
                        
                            //alert.dispose();
                            refreshTheme();
                        }

                    }
                    );

                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                    new listtransformationfront(previous).show();

                });
                ajouttransformation.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {

                        refreshTheme();
                    }

                });
                System.out.println("add Transformation");
                /////////////////UP VOTE////////////////////////
                Upvote.addPointerPressedListener(l -> {
                    new upvotetransformation(r).show();
                    new listtransformationfront(previous).show();
                });
                /////////////////DOWN VOTE////////////////////////
                Downvote.addPointerPressedListener(l -> {
                    new downvotetransformation(r).show();
                    new listtransformationfront(previous).show();
                });
                

                addAll(c3);
}
           

            

        }

    

}
