/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.Userservices;
import com.mycompany.myapp.utils.SessionManager;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Admin  extends BaseForm{
    public  ArrayList<User> users=new ArrayList<User>();
    public Admin(){
        
  
            
               // Userservices us=new Userservices();
                  users=Userservices.getInstance().getAllUsers();
      Container list =new Container(BoxLayout.y());
       list.setScrollableY(true);
       int i=1;
       for(User u : users){
           MultiButton Usernum =new MultiButton("User "+i+" :");
           Label btnnom =new Label();
           Label btnprenom=new Label();
           Label btnemail=new Label();
           Label btnpassword=new Label();
           Label btntel=new Label( );
          i++;
 btnnom.setText("Name :"+u.getNom());
  btnprenom.setText("LastName :"+u.getPrenom());
  btnemail.setText("Email :"+u.getMail());
    btnpassword.setText("Birth Date"+u.getDate_Naissance());

   btntel.setText("Phone number :"+String.valueOf(u.getTelephone_Number()));

list.add(Usernum); 
list.add(btnnom);
list.add(btnprenom);
list.add(btnemail);
list.add(btnpassword);
list.add(btntel);
       }
      
        add(list);
            
      
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

   
    }

