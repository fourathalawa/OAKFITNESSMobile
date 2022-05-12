/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Repas;
import com.mycompany.myapp.services.RepasService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Heni Nechi
 */
public class ListRepasForm extends Form{
     Form current;
     private EncodedImage enc;
    Image image;
    ImageViewer imgv;
    private String url = "http://127.0.0.1:8000/public/uploads/images";
     public ListRepasForm(Form previous) {
          super(BoxLayout.y());
          current = this;
        setTitle("List Meals");
        Button btnAdd = new Button("Add Meal");
        add(btnAdd);
        btnAdd.addActionListener(e -> new AjoutRepas(current).show());
        ArrayList<Repas> list = RepasService.getInstance().getAllMeals();
        
        for(Repas r:list)
        {
            Label Type = new Label("Breakfest :" +r.getPDej());
            add(Type);
            Label Name = new Label("Lunch :" +r.getDej());
            add(Name);
            Label Muscle = new Label("Dinner :" +r.getDinn());
            add(Muscle);
            Label Video = new Label("Calorie :" +r.getCalorie());
            add(Video);
            Label Description = new Label("Rest Or Active :" +r.getRestOrActive());
            add(Description);
            Button btnDelete = new Button("Delete Meal");
            Button btnEdit = new Button("Edit Meal");
        btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    RepasService.getInstance().Deletrepas(r);
                }
        });
        //image
            try {              
                enc=EncodedImage.create("/hhh.png");
            } catch (IOException ex) {
            }
            image=URLImage.createToStorage(enc,r.getImage(),url+r.getImage(),URLImage.RESIZE_SCALE).scaled(700, 300);
            imgv=new ImageViewer(image);
            add(imgv);
            //endimage
        add(btnDelete);
        btnEdit.addActionListener(ex -> new editRepas(r,current).show());
        add(btnEdit);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
