/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkManager;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.CheckBox;
import com.mycompany.myapp.entities.Transformation;
import com.mycompany.myapp.services.servicetransformationfront;

import java.util.ArrayList;
import com.codename1.ui.Image;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ext.filechooser.FileChooser;
import com.mycompany.myapp.utils.statics;
/**
 *
 * @author kriaa
 */
public class addTransformation extends Form{
Form previous;

    private String fileNameInServer = "";

    public addTransformation(){

 setTitle("Add a new Transformation");
        setLayout(BoxLayout.y());
        //TextField IdProduit = new TextField("", "Product ID");
        TextField TitreImage = new TextField("", "Transformation Title");
        TextField DescreptionImage = new TextField("", " Descreption ");
        TextField ImageAvant = new TextField("", " Image Before");
        TextField ImageApres = new TextField("", "Image After");
        TextField PoidAvant = new TextField("", "Weight Before");
        TextField PoidApres = new TextField("", "Weight After");
        TextField TailleAvant = new TextField("", "Height Before");
        TextField TailleApres = new TextField("", "Height After");
        Button btnValider = new Button("Add Transforamtion");
//
        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Image Transformation Before");
        testImage.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        String pathLogo=file;
                        String exx=file.substring(file.lastIndexOf("."));
                        
                        ImageAvant.setText(pathLogo);
                        System.out.println("path :"+pathLogo+ " extension :" +exx);
                        
                        try {
                            Image img2 = Image.createImage(file);
                            this.add(new Label(img2));
                            this.revalidate();
                        } catch (Exception ex) {
                        }


                    }
               });
            }
        });
  CheckBox multiSelect2 = new CheckBox("Multi-select");
        Button testImage2 = new Button("Browse Image Transformation After");
        testImage2.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        String pathLogo=file;
                        String exx=file.substring(file.lastIndexOf("."));
                        
                        ImageApres.setText(pathLogo);
                        System.out.println("path :"+pathLogo+ " extension :" +exx);
                        
                        try {
                            Image img = Image.createImage(file);
                            this.add(new Label(img));
                            this.revalidate();
                        } catch (Exception ex) {
                        }


                    }
               });
            }
        });
 this.add(testImage);
 this.add(testImage2);
Font materialFontAjout = FontImage.getMaterialDesignFont();
        int size = Display.getInstance().convertToPixels(5, true);
        materialFontAjout = materialFontAjout.derive(size, Font.STYLE_PLAIN);
        Button Delete = new Button("Delete");
        Delete.setIcon(FontImage.create("\uea4c", Delete.getUnselectedStyle(), materialFontAjout));
//MyApplication

      
        btnValider.addActionListener((e) -> {
            //serviceclub c = new serviceclub();
//Produit r = new Produit(0,"teststat","teststat","teststat",14,1,"test",23);
              
            Transformation r = new Transformation(TitreImage.getText(),DescreptionImage.getText(),ImageAvant.getText(), ImageApres.getText(),Float.parseFloat(PoidAvant.getText()),Float.parseFloat(PoidApres.getText()),Float.parseFloat(TailleAvant.getText()),Float.parseFloat(TailleApres.getText()));
            System.out.println("data  ==" + r);
            servicetransformationfront.getInstance().addTransformation(r);
            
   //
     Dialog alert = new Dialog("Success");
                    SpanLabel message = new SpanLabel("Transformation  added !");
                    alert.add(message);
                    Button ok = new Button("Confirmer");
                    ok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                        new listtransformationfront(previous).show();
                        refreshTheme();
                        }
                    }
                    );
                    alert.add(ok);
                    alert.showDialog();

        });

        addAll(TitreImage, DescreptionImage, ImageAvant, ImageApres, PoidAvant,PoidApres,TailleAvant,TailleApres, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new listtransformationfront(this).show());

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }

}

