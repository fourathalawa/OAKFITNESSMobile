/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Repas;
import com.mycompany.myapp.services.RepasService;
import java.text.ParseException;

/**
 *
 * @author Heni Nechi
 */
public class editRepas extends Form {

    Form current;
    TextField tf_pdej, tf_dej, tf_din, tf_calorie, tf_restoractive,image;

    public editRepas(Repas r,Form previous) {
        super(BoxLayout.y());

        current = this;
        setTitle("Add Exercice");
        getContentPane().setScrollVisible(false);

        tf_pdej = new TextField(r.getPDej(), "Breakfast");
        tf_dej = new TextField(r.getDej(), "Lunch");
        tf_din = new TextField(r.getDinn(), "Dinner");
        tf_calorie = new TextField(String.valueOf(r.getCalorie()), "Calorie");
        tf_restoractive = new TextField(r.getRestOrActive(), "Rest or Active");
        image =new TextField("", "image");
        Button btnadd = new Button("Edit meal");
//choose file
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
                        image.setText(pathLogo);
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
        //endchoosefile
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                r.setPDej(tf_pdej.getText().toString());
                r.setDej(tf_dej.getText().toString());
                r.setDinn(tf_din.getText().toString());
                float id = Float.parseFloat(tf_calorie.getText().toString());
                r.setCalorie((int) id);
                r.setRestOrActive(tf_restoractive.getText().toString());
                r.setImage(image.getText().toString());
                try {
                    RepasService.getInstance().editRepas(r);
                    new ListRepasForm(previous).show();
                        refreshTheme();
                } catch (ParseException ex) {

                }
            }
        });
        addAll(tf_pdej, tf_dej, tf_din, tf_calorie, tf_restoractive,testImage, btnadd);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
