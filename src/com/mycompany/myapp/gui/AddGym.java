/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.services.Challengeservices;
import com.mycompany.myapp.services.Salledesportservices;
import com.mycompany.myapp.utils.SessionManager;

/**
 *
 * @author User
 */
public class AddGym extends BaseForm {
 TextField Name,adress,price;
     public AddGym(){
     setTitle("Add new gym");
        setLayout(BoxLayout.y());
         Name=new TextField("","Gym name :");
         adress=new TextField("","Adress :"); 
         price=new TextField("","Price per session :");
       
        Button submit=new Button("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Salledesportservices us=new Salledesportservices();
                us.newgym(Name, adress, price,SessionManager.getId());
               new showuser().show();
            }
        });
        addAll(Name,adress,price,submit);
}
}
