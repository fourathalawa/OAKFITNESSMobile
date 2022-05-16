/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.SalleDeSport;
import com.mycompany.myapp.services.Salledesportservices;
import com.mycompany.myapp.utils.SessionManager;

/**
 *
 * @author User
 */
public class EditGym extends BaseForm {
     TextField Name,adress,price;
    public EditGym(TextField idsalle){
        SalleDeSport salle = new SalleDeSport();
        int idsal = Integer.parseInt(idsalle.getText().toString());
        salle = Salledesportservices.getInstance().getSalle(idsal);
         setTitle("Add new gym");
        setLayout(BoxLayout.y());
         Name=new TextField(salle.getNom(),"Gym name :");
         adress=new TextField(salle.getAdresse(),"Adress :"); 
         price=new TextField(String.valueOf(salle.getPrixSeance()),"Price per session :");
        Button submit=new Button("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Salledesportservices us=new Salledesportservices();
              us.editSalle(Name, adress, price);
               new showuser().show();
            }
        });
        addAll(Name,adress,price,submit);
    }
}
