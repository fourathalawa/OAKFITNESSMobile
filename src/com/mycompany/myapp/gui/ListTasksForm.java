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

import java.util.ArrayList;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    Form current;

    public ListTasksForm(Form previous) {
        setTitle("List tasks");
        current = this; //Back 

        SpanLabel sp = new SpanLabel();
        ArrayList<Task> pub = (ServiceTask.getInstance().getAllTasks());
        int i;
        for (Task e : pub) {
Commentaire c = new Commentaire();
            Label Type = new Label("Date :" + e.getDatepublication());
            
            Label Name = new Label(e.getPublication());
            Label Muscle = new Label("Name :" + e.getUsernamep());
            Button view = new Button("view");
            Button modifier = new Button("edit");
            Button btnDelete = new Button("Delete");

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceTask.getInstance().Delete(e.getIdpublication());
                    btnDelete.addActionListener(e -> new ListTasksForm(current).show());
new ListTasksForm(previous).show();
                        refreshTheme();

                }
            });

          modifier.addActionListener(ex -> new updateClub(e,current).show());
          view.addActionListener(ex -> new ListCommentaire(e,c,current).show());
add(Type);
            add(Name);
            add(Muscle);

            add(view);
            add(modifier);
            add(btnDelete);


        }

        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
