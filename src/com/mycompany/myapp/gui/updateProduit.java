/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Dialog;
import com.codename1.components.SpanLabel;

import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.serviceproduit;

import com.mycompany.myapp.gui.Listproduit;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.CheckBox;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Image;

/**
 *
 * @author kriaa
 */
public class updateProduit extends Form{
Form previous;

    public updateProduit(Produit ev) {
        super("Newsfeed", BoxLayout.y());

        setTitle("Modifier Votre Produit ");

        TextField Nom = new TextField(ev.getNomProduit(), "NomProduit", 20, TextField.ANY);
        Nom.setUIID("TextFieldBlack");

        TextField Description = new TextField(ev.getDescrProduit(), "DescrProduit", 20, TextField.ANY);
        Description.setUIID("TextFieldBlack");

        TextField Category = new TextField(ev.getCategProduit(), "CategProduit ", 20, TextField.ANY);
        Category.setUIID("TextFieldBlack");

        TextField Prix = new TextField(((Float)ev.getPrixProduit()).toString(), "PrixProduit", 20, TextField.ANY);
        Prix.setUIID("TextFieldBlack");
        
        TextField ImageProduit = new TextField(ev.getImageProduit(), "ImageProduit", 20, TextField.ANY);
        ImageProduit.setUIID("TextFieldBlack");
        
        TextField IsAvailable = new TextField(((Integer)ev.getIsAvailable()).toString(), "IsAvailable", 20, TextField.ANY);
        IsAvailable.setUIID("TextFieldBlack");
    
        TextField StockProduit = new TextField(((Integer)ev.getStockProduit()).toString(), "StockProduit", 20, TextField.ANY);
        StockProduit.setUIID("TextFieldBlack");

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
//MyApplication

        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        String pathLogo=file;
                        String exx=file.substring(file.lastIndexOf("."));
                        
                        ImageProduit.setText(pathLogo);
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

        //OnClick Button
        btnModifier.addPointerPressedListener(l -> {

            ev.setNomProduit(Nom.getText());
            ev.setDescrProduit(Description.getText());
            ev.setCategProduit(Category.getText());
            ev.setPrixProduit(Float.parseFloat(Prix.getText()));
            ev.setImageProduit(ImageProduit.getText());
            ev.setIsAvailable(Integer.parseInt(IsAvailable.getText()));
            ev.setStockProduit(Integer.parseInt(StockProduit.getText()));
  
            //Appel a la methode UPDATE
            if (serviceproduit.getInstance().modifierProduit(ev)) {
                //If True
                                   Dialog alert = new Dialog("Success");
                    SpanLabel message = new SpanLabel("product modified !");
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
                 //new Listproduit(res).show();
            }
        });

        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l6 = new Label("");
        Label l7 = new Label("");
        Label l8 = new Label("");
        

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
             
                new FloatingHint(Nom),
                new FloatingHint(Description),
                new FloatingHint(Category),
                new FloatingHint(Prix),
                new FloatingHint(ImageProduit),
                new FloatingHint(IsAvailable),
                new FloatingHint(StockProduit),
                btnModifier
             
        );

        add(content);
        show();
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Listproduit(this).show());
    }

}
