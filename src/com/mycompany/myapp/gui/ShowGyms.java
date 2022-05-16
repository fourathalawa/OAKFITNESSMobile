/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.SalleDeSport;
import com.mycompany.myapp.services.Salledesportservices;
import com.mycompany.myapp.services.Userservices;
import com.mycompany.myapp.utils.SessionManager;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ShowGyms extends BaseForm{
    public  ArrayList<SalleDeSport> salles=new ArrayList<SalleDeSport>();
    public ShowGyms()
    {
                salles=Salledesportservices.getInstance().getMygyms(SessionManager.getId());
      Container list =new Container(BoxLayout.y());
       list.setScrollableY(true);
       int i=1;
       for(SalleDeSport u : salles){
           MultiButton Usernum =new MultiButton("Gym "+i+" :");
           Label btnnom =new Label();
           Label btnadress=new Label();
           Label btnprice=new Label();
           TextField idsalle= new TextField();
           
Button edit = new Button("Edit GYM");
Button delete = new Button("Delete GYM");


edit.addActionListener(ex ->new EditGym(idsalle).show());
delete.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer votre compte!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                     Salledesportservices.getInstance().deleteGYM(idsalle);
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        new ShowGyms().show();

                        refreshTheme();
             //            new ListUserForm(previous).show();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

            });          i++;
          
 btnnom.setText("Name :"+u.getNom());
  btnadress.setText("Adress :"+u.getAdresse());
  btnprice.setText("Price per session :"+u.getPrixSeance());
    idsalle.setText(""+u.getId());
    idsalle.setVisible(false);
list.add(Usernum);
list.add(idsalle);
list.add(btnnom);
list.add(btnadress);
list.add(btnprice);
list.add(edit);
list.add(delete);
       }
      
        add(list);
            
    }
}
