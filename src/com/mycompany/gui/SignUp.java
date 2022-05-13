/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author Fourat
 */


import com.codename1.capture.Capture;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;

import static com.codename1.ui.Component.LEFT;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.Userservices;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.io.IOException;
import java.io.OutputStream;





public class SignUp extends BaseForm {
     //String pathToBeStored ="file://home/1652073425984.jpg";
       public SignUp(Resources res) {
       super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //setUIID("LoginForm");
        setTitle("OAK FITNESS");
       // setLayout(BoxLayout.y());
        Label titrelabel= new Label("Sign UP");
        titrelabel.setVisible(true);
        
        TextField name = new TextField("", "Name", 20, TextField.ANY);
        TextField lastname = new TextField("", "Last Name", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField phone = new TextField("", "Phone Number", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
      Picker  date = new Picker();
       date.setType(Display.PICKER_TYPE_DATE);
        name.getAllStyles().setMargin(LEFT, 0);
        lastname.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
         phone.getAllStyles().setMargin(LEFT, 0);
        Label passwordLabel= new Label("Password is too short","ControlLabel");
        passwordLabel.setVisible(false);
         Label dateLabel= new Label("under age","ControlLabel");
        dateLabel.setVisible(false);
 Label phonelabel= new Label("Enter your phone number","ControlLabel");
        phonelabel.setVisible(false);
        Label nameLabel= new Label("Enter your name","ControlLabel");
        nameLabel.setVisible(false);
        Label lastnameLabel= new Label("Enter your last name","ControlLabel");
        lastnameLabel.setVisible(false);
        Label emailLabel= new Label("Enter a correct email","ControlLabel");
        emailLabel.setVisible(false);
        
        Button signup = new Button ("Sign Up");
         signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Userservices us=new Userservices();
                us.addUser(name,lastname,email,phone,date,password);
               
            }
        });
        Container container = BoxLayout.encloseY(
               
            
                nameLabel,
                BorderLayout.center(name),
                lastnameLabel,       
                BorderLayout.center(lastname),
                phonelabel,       
                BorderLayout.center(phone),
                emailLabel,       
                BorderLayout.center(email),
                dateLabel,
                                BorderLayout.center(date),
                passwordLabel,      
                BorderLayout.center(password),
           
               signup

        );
         container.setScrollableY(true);
        add(BorderLayout.CENTER, container);
        container.setScrollableY(true);
        container.setScrollVisible(false);
       }
 
}