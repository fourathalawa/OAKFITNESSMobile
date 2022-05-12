/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceCommentaire;
import com.mycompany.myapp.services.ServiceReclamation;
import java.text.ParseException;




/**
 *
 * @author Heni Nechi
 */
public class AddReport extends Form {
    Form current;
    TextField tf_creator,tf_titre,tf_descr,tf_adresse,tf_type;
    public AddReport(Commentaire e,Form previous)
    {
       super(BoxLayout.y());
     
     current = this; 
       
        setTitle("Reclam");
        getContentPane().setScrollVisible(false);
        
        
    TextField tfName = new TextField("", "report");
        Button btnValider = new Button("Add report");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation t = new Reclamation(tfName.getText().toString(),e.getCommentaire(),e.getIdcommentaire());
                        if (ServiceReclamation.getInstance().addReclam(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfName, btnValider);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, ex-> previous.showBack());
    }
    
}