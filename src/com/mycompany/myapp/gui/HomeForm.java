/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.utils.SessionManager;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ask a question");
        Button btnListTasks = new Button("Forum");
        Button btnListReport = new Button("Reports");

        
        btnAddTask.addActionListener(e-> new AddTaskForm(current).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        
        add(btnAddTask);
        add(btnListTasks);
       
       current = this; //Récupération de l'interface(Form) en cours
        int session = SessionManager.getRole(); /////////////////
    
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        Button btnListEvents = new Button("Events");
        Button btnAddTrans = new Button("Transformation");
        Button btnAddshop = new Button(" Products");
        Button btnProfile = new Button("Profile");
        Button btnUsers = new Button("Users");
        if(session==3){
        btnUsers.addActionListener(e -> new Admin().show());
add(btnUsers);
btnListReport.addActionListener(e-> new ListReport(current).show());
 add(btnListReport);
        }



        if(session!=0){
        btnListEvents.addActionListener(e -> new ListEventsForm(current).show());
        }else{
        btnListEvents.addActionListener(e -> {
            new ListEvents(current).show();
        });
        }

       if(session!=0){
         btnAddshop.addActionListener(e -> new Listproduit(current).show());
        }else{
        btnAddshop.addActionListener(e -> new Listproduitfront(current).show());
        }
 if(session!=0){
         btnAddTrans.addActionListener(e -> new listtransformation(current).show());
        }else{
        btnAddTrans.addActionListener(e -> new listtransformationfront(current).show());
        }


        add(btnAddshop);
        add(btnAddTrans);



        add(btnListEvents);
        
        
        
        
        Button btnListReaps = new Button("Meals");
        if(session!=0){
        btnListReaps.addActionListener(e -> new ListRepasForm(current).show());
        }else{
        btnListReaps.addActionListener(e -> new ListRepas(current).show());
        }
        add(btnListReaps);
        
         
         
        Button btnListExercice = new Button("Exercice");
        if(session!=0){
        btnListExercice.addActionListener(e -> new ListExerciceForm(current).show());
        }
        else{
        btnListExercice.addActionListener(e -> new ListExercice(current).show());
        }
        add(btnListExercice);
        
        btnProfile.addActionListener(e -> new showuser().show());
        add(btnProfile);

    }   
        
    
    
}
