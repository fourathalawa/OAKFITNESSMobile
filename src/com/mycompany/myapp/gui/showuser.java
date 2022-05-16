/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.Challengeservices;
import com.mycompany.myapp.services.Salledesportservices;
import com.mycompany.myapp.services.Userservices;
import com.mycompany.myapp.utils.SessionManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author User
 */
public class showuser extends BaseForm {
BaseForm current;
    public showuser() {
    User t = new User();
    Userservices u = new Userservices();
    setTitle("Profile User");
         SpanLabel sp = new SpanLabel();
           setLayout(BoxLayout.y());
 String url = "http://localhost:8000/public/" + "images/" + SessionManager.getProfilepicture();
         Form current;
  Container c3 = new Container(BoxLayout.y());
         Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            System.out.println(url);
            URLImage urlim = URLImage.createToStorage(enc, SessionManager.getProfilepicture(), url);
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);     
       /*  String img = SessionManager.getProfilepicture();
         System.out.println(img);
            ImageViewer imgV =null;
            try{
            imgV =new ImageViewer(Image.createImage("/"+SessionManager.getProfilepicture()));
            } catch (IOException ex) {   
        }*/
              SpanLabel spl = new SpanLabel("nom: " + "  " + SessionManager.getName());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + SessionManager.getLastname());
                SpanLabel sp7 = new SpanLabel("Mail: " + "  " + SessionManager.getEmail());
                SpanLabel sp9 = new SpanLabel("telephone"+" "+SessionManager.getTelephone());
                SpanLabel sp5 = new SpanLabel("image: " + "  " + SessionManager.getProfilepicture());
                SpanLabel sp2 = new SpanLabel("password: " + "  " + SessionManager.getPassword());
        //  SpanLabel sp3 = new SpanLabel("date naissance: " + "  " + SessionManager.getDate());
          addAll(imgV,spl,spl2,sp7,sp9,sp2);
          t.setNom(SessionManager.getName());
                    t.setPrenom(SessionManager.getLastname());
                    t.setMail(SessionManager.getEmail());
                     t.setTelephone_Number(SessionManager.getTelephone());
  t.setPassword(SessionManager.getPassword());
                                                    Button Delete1 = new Button("Delete");
                                     Delete1.getAllStyles().setBgColor(0xF36B08);
            Delete1.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer votre compte!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                     Userservices.getInstance().deleteUser(SessionManager.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        new Login().show();

                        refreshTheme();
             //            new ListUserForm(previous).show();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

            });
            if(SessionManager.getRole()==0){
            Button addchallenge = new Button("MY New Challenge");

addchallenge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                new newChallnege().show();
               
            }
        });
Button challenge = new Button("My Challenge");

challenge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
Challengeservices ch = new Challengeservices();
ch.getChallenge(SessionManager.getId());
                new showChallenge().show();
                System.out.println("done");
            }
        });
  add(addchallenge);
    add(challenge);
       
            }else if(SessionManager.getRole()==2){
                Button salle = new Button("My Gyms");

salle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
Salledesportservices sal = new Salledesportservices();

                new ShowGyms().show();
                System.out.println("done");
            }
        });
  add(salle);
                
            }
                                        Button lModifier = new Button("Modifier");
                                         
            lModifier.addActionListener(ex -> new editUser().show());
  Button log_out = new Button("Log OUT");
  log_out.addActionListener(ex -> new Login().show());
  

            add(Delete1);
            add(lModifier);
            add(log_out);
       
  

}}
