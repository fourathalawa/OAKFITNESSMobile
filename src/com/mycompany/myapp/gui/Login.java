/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.mycompany.myapp.services.Userservices;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.SessionManager;
/**
 *
 * @author User
 */
public class Login extends BaseForm{
        private Resources theme;

     TextField tf_email ;
    public Login() {
          setTitle("Log IN");
        setLayout(BoxLayout.y());
         tf_email=new TextField("","Email");
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Button btnlogin=new Button("Login");
          Button forget= new Button("Did you forget your password?");
        Button btnreg= new Button(" Don't have an account ?SignUp...");
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Userservices us=new Userservices();
               us.signin(tf_email,password);
              
            }
        });
        btnreg.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                new SignUp(theme).show();
                  
              }
          });
         forget.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
               // new forgetPassword().show();
                  
              }
          });
        addAll(tf_email,password,btnlogin,btnreg);
    }

  
}
