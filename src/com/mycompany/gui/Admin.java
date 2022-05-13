/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
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
import com.mycompany.entities.User;
import com.mycompany.services.Userservices;
import com.mycompany.utils.SessionManager;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Admin  extends BaseForm{
    public  ArrayList<User> users=new ArrayList<User>();
 
     
    public Admin(){
        
        //super(BoxLayout.y());
     //   Toolbar tb = getToolbar();
      //  tb.setTitleCentered(false);
      
   Button menuButton = new Button("");
        menuButton.setUIID("Title");
      
        
      

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(SessionManager.getName()+""+SessionManager.getLastname(), "Title"),
                                    new Label(SessionManager.getEmail(), "SubTitle")
                                )
                            )
                );
        
      
                        
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        Button btn=new Button("getUSers");
            
                Userservices us=new Userservices();
                String url = "http://localhost:8000/user/allusers";
        req = new ConnectionRequest(url,false);
       
      
         users = new ArrayList<>();

     
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       JSONParser j=new JSONParser();
    String json = new String(req.getResponseData());      
    System.out.println(json);

               if(json!=null){
                   
                   users=getList();
               }
               req.removeResponseListener(this);
        }});
      Container list =new Container(BoxLayout.y());
       list.setScrollableY(true);
       for(User u : users){
           MultiButton btnnom =new MultiButton(u.getNom());
           MultiButton btnprenom=new MultiButton(u.getPrenom());
           MultiButton btnemail=new MultiButton(u.getMail());
           MultiButton btnpassword=new MultiButton(u.getPassword());
           MultiButton btntel=new MultiButton(String.valueOf(u.getTelephone_Number()));
 btnnom.setTextLine2(u.getNom());
  btnprenom.setTextLine2(u.getPrenom());
  btnemail.setTextLine2(u.getMail());
  btnpassword.setTextLine2(u.getPassword());
//  btnrole.setTextLine2(u.getRole());
  btntel.setTextLine2(String.valueOf(u.getTelephone_Number()));
list.add(btnnom);
list.add(btnprenom);
list.add(btnemail);
list.add(btnpassword);
list.add(btntel);
       }
      
        add(list);
            
      
        add(btn);
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

