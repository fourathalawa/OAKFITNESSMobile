/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.showuser;
import com.mycompany.myapp.services.Userservices;
import com.mycompany.myapp.utils.SessionManager;
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
    public editUser()
    {
       super(BoxLayout.y());
     
       
        setTitle("Edit User");
        getContentPane().setScrollVisible(false);
        
        
        nom=new TextField(SessionManager.getName(),"Name");
       prenom=new TextField(SessionManager.getLastname(),"Last Name"); 
         phone=new TextField(String. valueOf(SessionManager.getTelephone()),"Phone number");
         email=new TextField(SessionManager.getEmail(),"Email");
         TextField image =new TextField("", "image");
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
      password = new TextField("", "Password", 20, TextField.PASSWORD);

    
        Button btnupd=new Button("Profile edit");
        
        btnupd.addActionListener(new ActionListener() {
              
                SessionManager session= new SessionManager();
                  @Override
                public void actionPerformed(ActionEvent evt) {
               session.setName(nom.getText().toString());
               session.setLastname(prenom.getText().toString());
             session.setEmail(email.getText().toString());
             session.setTelephone(Long.parseLong(phone.getText().toString()));
                    session.setPassword(password.getText().toString());
                    Userservices.getInstance().editUser(nom,prenom,email,phone,password,image);
                }
        });
        addAll(nom,prenom,email,phone,password,image,testImage,btnupd);
    }
    
}