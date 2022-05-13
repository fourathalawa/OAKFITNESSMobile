/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.services.Challengeservices;
import com.mycompany.utils.SessionManager;

/**
 *
 * @author User
 */
public class newChallnege extends BaseForm {
     TextField poidint,poidobj,poidnv,taille;
     public newChallnege(){
     setTitle("Sign UP");
        setLayout(BoxLayout.y());
         poidint=new TextField("","Cuurrent weight");
         taille=new TextField("","height"); 
         poidobj=new TextField("","Object weight");
         Label date1label= new Label();
                  Label date2label= new Label();
        Picker datedeb = new Picker();
        datedeb.setType(Display.PICKER_TYPE_DATE);
       Picker datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE);  
        Button submit=new Button("Submit");
       
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Challengeservices us=new Challengeservices();
                us.newchallenge(poidint, poidobj, taille, datedeb, datefin,SessionManager.getId());
               new showuser().show();
            }
        });
        addAll(poidint,poidobj,taille,date1label,datedeb,date2label,datefin,submit);
}
}
