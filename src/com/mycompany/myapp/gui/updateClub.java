/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
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
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;
import com.codename1.ui.events.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.text.ParseException;




/**
 *
 * @author Heni Nechi
 */
public class updateClub extends Form {
    Form current;
    TextField tf_creator,tf_titre,tf_descr,tf_adresse,tf_type;
    public updateClub(Task e,Form previous)
    {
       super(BoxLayout.y());
     
     current = this; 
       
        setTitle("Edit");
        getContentPane().setScrollVisible(false);
        
        
        tf_creator=new TextField(e.getPublication(),"creator");
       
        Picker tf_date = new Picker();
       
        Button btnadd=new Button("Edit");
        
        btnadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                e.setPublication(tf_creator.getText().toString());
                
            
                    
                        ServiceTask.getInstance().modifierClub(e);



  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("editing publication");
  status.setShowProgressIndicator(true);
  status.show();
  // ... Some time later you must clear it
current.show();

                    
                }
        });


        addAll(tf_creator,btnadd);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, ex-> previous.showBack());
    }
    
}