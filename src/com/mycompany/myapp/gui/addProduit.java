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
import com.codename1.ui.Image;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.serviceproduit;
import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.io.MultipartRequest;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.AutoCompleteTextField;



import java.io.IOException;
import com.codename1.ext.filechooser.FileChooser;

import com.mycompany.myapp.utils.statics;


/**
 *
 * @author kriaa
 */
public class addProduit extends Form{
Form previous;

    private String fileNameInServer = "";

    public addProduit(){

 setTitle("Add a new product");
        setLayout(BoxLayout.y());
        //TextField IdProduit = new TextField("", "Product ID");
        TextField NomProduit = new TextField("", "Product Name");
        TextField CategProduit = new TextField("", " Category ");
        TextField DescrProduit = new TextField("", " Descreption");
        TextField PrixProduit = new TextField("", "Price");
        TextField IsAvailable = new TextField("", "availability");
        TextField ImageProduit = new TextField("", "Image");
        TextField StockProduit = new TextField("", "Stock");
        Button btnValider = new Button("Add Product");

Font materialFontAjout = FontImage.getMaterialDesignFont();
        int size = Display.getInstance().convertToPixels(5, true);
        materialFontAjout = materialFontAjout.derive(size, Font.STYLE_PLAIN);
        Button Delete = new Button("Supprimer");
       // Delete.setIcon(FontImage.create("\uea4c", Delete.getUnselectedStyle(), materialFontAjout));
//MyApplication

        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e -> {
            if (FileChooser.isAvailable()) {

                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2 -> {
                    if (e2 != null && e2.getSource() != null) {

                        String file = (String) e2.getSource();
                        String pathLogo = file;
                        String exx = file.substring(file.lastIndexOf("."));
                        System.out.println(pathLogo);
                        ImageProduit.setText(pathLogo);
                        System.out.println("path :" + pathLogo + " extension :" + exx);

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
        //endchoosefile
        btnValider.addActionListener((e) -> {
              
            Produit r = new Produit(NomProduit.getText(),CategProduit.getText(),DescrProduit.getText(), Float.parseFloat(PrixProduit.getText()),Integer.parseInt(IsAvailable.getText()),ImageProduit.getText(),Integer.parseInt(StockProduit.getText()));
            System.out.println("data  ==" + r);
            serviceproduit.getInstance().addProduit(r);
            
   //
     Dialog alert = new Dialog("Success");
                    SpanLabel message = new SpanLabel("product added !");
                    alert.add(message);
                    Button ok = new Button("Confirmer");
                    ok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                        new Listproduit(previous).show();
                        refreshTheme();
                        }
                    }
                    );
                    alert.add(ok);
                    alert.showDialog();

        });
        addAll(NomProduit, CategProduit, DescrProduit, PrixProduit, IsAvailable,ImageProduit,StockProduit, btnValider);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Listproduit(this).show());

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }

}
