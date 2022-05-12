/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.entities.Commentaire;

import com.mycompany.myapp.services.ServiceTask;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceCommentaire;
import com.mycompany.myapp.services.ServiceReclamation;

import java.util.ArrayList;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author bhk
 */
public class ListReport extends Form {

    Form current;

    public ListReport(Form previous) {
        setTitle("List report");
        current = this; //Back 

        SpanLabel sp = new SpanLabel();
        ArrayList<Reclamation> pub = (ServiceReclamation.getInstance().getAllReclamations(TOP));
        int i;
        for (Reclamation e : pub) {
Commentaire c = new Commentaire();
            Label Type = new Label("description :" + e.getDescrreclam());
            
            Label Name = new Label("comment"+e.getCommentairerec());


                        Button btnDeleteccc = new Button("Delete Comment");

  btnDeleteccc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceReclamation.getInstance().Delete(e.getIdcommentreclam());
                    btnDeleteccc.addActionListener(e -> new ListTasksForm(current).show());
new ListReport(previous).show();
                        refreshTheme();

                }
            });
add(Type);
            add(Name);
            add(btnDeleteccc);


      

        }

        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
