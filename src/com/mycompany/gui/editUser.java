/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.entities.User;
import com.mycompany.gui.showuser;
import com.mycompany.services.Userservices;
import com.mycompany.utils.SessionManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author Fourat
 */
public class editUser extends BaseForm {
    TextField nom,prenom,phone,email;
     TextField password;
    public editUser() throws ParseException
    {
       super(BoxLayout.y());
     
       
        setTitle("Edit User");
        getContentPane().setScrollVisible(false);
        
        
        nom=new TextField(SessionManager.getName(),"Name");
       prenom=new TextField(SessionManager.getLastname(),"Last Name"); 
         phone=new TextField(String. valueOf(SessionManager.getTelephone()),"Phone number");
         email=new TextField(SessionManager.getEmail(),"Email");
        password=new TextField(SessionManager.getDate(),"Password");
        Picker tf_date = new Picker();
        tf_date.setType(Display.PICKER_TYPE_DATE);
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(SessionManager.getDate());
        tf_date.setDate(date1);
        Button btnupd=new Button("Profile event");
        
        btnupd.addActionListener(new ActionListener() {
              
                SessionManager session= new SessionManager();
                  @Override
                public void actionPerformed(ActionEvent evt) {
               session.setName(nom.getText().toString());
               session.setLastname(prenom.getText().toString());
             session.setEmail(email.getText().toString());
             session.setTelephone(Long.parseLong(phone.getText().toString()));
                    session.setDate(tf_date.getText().toString());
                    session.setPassword(password.getText().toString());
                    Userservices.getInstance().editprofile(session);
                }
        });
        addAll(nom,prenom,email,phone,tf_date,password,btnupd);
    }
    
}