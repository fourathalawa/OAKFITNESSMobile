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
import com.mycompany.myapp.services.servicetransformation;

import java.util.ArrayList;
import com.mycompany.myapp.utils.statics;
import com.codename1.components.SignatureComponent;


/**
 *
 * @author kriaa
 */
public class listtransformation extends Form {
public listtransformation(Form previous) {

        setTitle("Liste des Transformation(s)");

        servicetransformation es = new servicetransformation();
        ArrayList<Transformation> list = es.getAllTransformations();

        


            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
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

                Button Delete = new Button("Delete");
                c3.add(Delete);
c3.add("Leave ur Admin comment");
SignatureComponent sig = new SignatureComponent();
sig.addActionListener((evt)-> {
    
    Image img = sig.getSignatureImage();
    // Now we can do whatever we want with the image of this signature.
});
c3.add(sig);


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
                            new listtransformation(previous).show();
                        
                            //alert.dispose();
                            refreshTheme();
                        }

                    }
                    );

                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                    new listtransformation(previous).show();

                });
              

                addAll(c3);
}
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            

        }

    


}
